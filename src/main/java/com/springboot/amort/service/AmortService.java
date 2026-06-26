package com.springboot.amort.service;

import com.springboot.amort.context.InvokerContext;
import com.springboot.amort.dto.AmortRequest;
import com.springboot.amort.dto.AmortResponse;
import com.springboot.amort.dto.ScheduleRow;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class AmortService {

    private static final DateTimeFormatter MONTH_FORMAT = DateTimeFormatter.ofPattern("MMM yyyy");

    public AmortResponse calculate(AmortRequest request, InvokerContext invokerContext) {
        validate(request);

        double principal = request.getPrincipal();
        double monthlyRate = request.getAnnualRate() / 100.0 / 12.0;
        int months = request.getPeriodMonths();

        double monthlyPayment;
        if (monthlyRate == 0) {
            monthlyPayment = principal / months;
        } else {
            monthlyPayment = principal * monthlyRate * Math.pow(1 + monthlyRate, months)
                    / (Math.pow(1 + monthlyRate, months) - 1);
        }

        List<ScheduleRow> schedule = new ArrayList<>();
        double balance = principal;
        double totalInterest = 0;
        LocalDate inceptionDate = request.getInceptionDate();

        for (int i = 1; i <= months; i++) {
            double interest = balance * monthlyRate;
            double principalPaid = monthlyPayment - interest;

            if (i == months) {
                principalPaid = balance;
                monthlyPayment = principalPaid + interest;
            }

            balance = Math.max(0, balance - principalPaid);
            totalInterest += interest;

            schedule.add(new ScheduleRow(
                    inceptionDate.plusMonths(i).format(MONTH_FORMAT),
                    money(monthlyPayment),
                    money(principalPaid),
                    money(interest),
                    money(balance)
            ));
        }

        double roundedMonthlyPayment = schedule.isEmpty() ? 0 : schedule.get(0).getPayment();
        double totalRepayment = principal + totalInterest;

        // No PostgreSQL save is performed here. The calculation is stateless.
        return new AmortResponse(
                money(roundedMonthlyPayment),
                money(totalRepayment),
                money(totalInterest),
                schedule
        );
    }

    private void validate(AmortRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("Request body is required.");
        }
        if (request.getPrincipal() <= 0) {
            throw new IllegalArgumentException("Principal must be greater than zero.");
        }
        if (request.getAnnualRate() < 0) {
            throw new IllegalArgumentException("Annual rate cannot be negative.");
        }
        if (request.getPeriodMonths() <= 0) {
            throw new IllegalArgumentException("Period months must be greater than zero.");
        }
        if (request.getInceptionDate() == null) {
            throw new IllegalArgumentException("Inception date is required.");
        }
    }

    private double money(double value) {
        return BigDecimal.valueOf(value)
                .setScale(2, RoundingMode.HALF_UP)
                .doubleValue();
    }
}

package com.springboot.amort.dto;

import java.util.List;

public class AmortResponse {
    private double monthlyPayment;
    private double totalRepayment;
    private double totalInterest;
    private List<ScheduleRow> schedule;

    public AmortResponse() {}

    public AmortResponse(double monthlyPayment, double totalRepayment, double totalInterest, List<ScheduleRow> schedule) {
        this.monthlyPayment = monthlyPayment;
        this.totalRepayment = totalRepayment;
        this.totalInterest = totalInterest;
        this.schedule = schedule;
    }

    public double getMonthlyPayment() { return monthlyPayment; }
    public void setMonthlyPayment(double monthlyPayment) { this.monthlyPayment = monthlyPayment; }

    public double getTotalRepayment() { return totalRepayment; }
    public void setTotalRepayment(double totalRepayment) { this.totalRepayment = totalRepayment; }

    public double getTotalInterest() { return totalInterest; }
    public void setTotalInterest(double totalInterest) { this.totalInterest = totalInterest; }

    public List<ScheduleRow> getSchedule() { return schedule; }
    public void setSchedule(List<ScheduleRow> schedule) { this.schedule = schedule; }
}

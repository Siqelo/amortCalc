package com.springboot.amort.dto;

import java.time.LocalDate;

public class AmortRequest {
    private double principal;
    private double annualRate;
    private int periodMonths;
    private LocalDate inceptionDate;

    public double getPrincipal() { return principal; }
    public void setPrincipal(double principal) { this.principal = principal; }

    public double getAnnualRate() { return annualRate; }
    public void setAnnualRate(double annualRate) { this.annualRate = annualRate; }

    public int getPeriodMonths() { return periodMonths; }
    public void setPeriodMonths(int periodMonths) { this.periodMonths = periodMonths; }

    public LocalDate getInceptionDate() { return inceptionDate; }
    public void setInceptionDate(LocalDate inceptionDate) { this.inceptionDate = inceptionDate; }
}

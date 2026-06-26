package com.springboot.amort.dto;

public class ScheduleRow {
    private String date;
    private double payment;
    private double principal;
    private double interest;
    private double balance;

    public ScheduleRow() {}

    public ScheduleRow(String date, double payment, double principal, double interest, double balance) {
        this.date = date;
        this.payment = payment;
        this.principal = principal;
        this.interest = interest;
        this.balance = balance;
    }

    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }

    public double getPayment() { return payment; }
    public void setPayment(double payment) { this.payment = payment; }

    public double getPrincipal() { return principal; }
    public void setPrincipal(double principal) { this.principal = principal; }

    public double getInterest() { return interest; }
    public void setInterest(double interest) { this.interest = interest; }

    public double getBalance() { return balance; }
    public void setBalance(double balance) { this.balance = balance; }
}

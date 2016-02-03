package com.example.poorwa.shantibanapp;

/**
 * Created by poorwa on 29/1/16.
 */
public class AnnualMaintenance {
    private String memberName, plotNumber, paymentDate, amountPaid,
    lateFeeFine;

    public AnnualMaintenance() {
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getPlotNumber() {
        return plotNumber;
    }

    public void setPlotNumber(String plotNumber) {
        this.plotNumber = plotNumber;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(String amountPaid) {
        this.amountPaid = amountPaid;
    }

    public String getLateFeeFine() {
        return lateFeeFine;
    }

    public void setLateFeeFine(String lateFeeFine) {
        this.lateFeeFine = lateFeeFine;
    }
}

package com.example.finance_calc.models.holders;

import android.widget.TextView;

public class CreditTextViewHolder {

    private TextView viewAnInitialFee;
    private TextView viewMonthlyPayment;
    private TextView viewOverpayment;
    private TextView viewTotalRepaymentAmount;

    public CreditTextViewHolder(TextView viewAnInitialFee, TextView viewMonthlyPayment, TextView viewOverpayment, TextView viewTotalRepaymentAmount) {
        this.viewAnInitialFee = viewAnInitialFee;
        this.viewMonthlyPayment = viewMonthlyPayment;
        this.viewOverpayment = viewOverpayment;
        this.viewTotalRepaymentAmount = viewTotalRepaymentAmount;
    }

    public TextView getViewAnInitialFee() {
        return viewAnInitialFee;
    }

    public TextView getViewMonthlyPayment() {
        return viewMonthlyPayment;
    }

    public TextView getViewOverpayment() {
        return viewOverpayment;
    }

    public TextView getViewTotalRepaymentAmount() {
        return viewTotalRepaymentAmount;
    }
}

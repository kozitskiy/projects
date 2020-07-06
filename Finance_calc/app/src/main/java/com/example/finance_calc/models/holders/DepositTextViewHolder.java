package com.example.finance_calc.models.holders;

import android.widget.TextView;

public class DepositTextViewHolder {

    private TextView viewMonthlyIncome;
    private TextView viewDepositIncomeByAllMonth;
    private TextView viewReplenishmentAmountByAllDepositPeriod;
    private TextView viewAllDepositPeriodIncome;

    public DepositTextViewHolder(TextView viewMonthlyIncome, TextView viewDepositIncomeByAllMonth, TextView viewReplenishmentAmountByAllDepositPeriod, TextView viewAllDepositPeriodIncome) {
        this.viewMonthlyIncome = viewMonthlyIncome;
        this.viewDepositIncomeByAllMonth = viewDepositIncomeByAllMonth;
        this.viewReplenishmentAmountByAllDepositPeriod = viewReplenishmentAmountByAllDepositPeriod;
        this.viewAllDepositPeriodIncome = viewAllDepositPeriodIncome;
    }

    public TextView getViewMonthlyIncome() {
        return viewMonthlyIncome;
    }

    public TextView getViewDepositIncomeByAllMonth() {
        return viewDepositIncomeByAllMonth;
    }

    public TextView getViewReplenishmentAmountByAllDepositPeriod() {
        return viewReplenishmentAmountByAllDepositPeriod;
    }

    public TextView getViewAllDepositPeriodIncome() {
        return viewAllDepositPeriodIncome;
    }
}

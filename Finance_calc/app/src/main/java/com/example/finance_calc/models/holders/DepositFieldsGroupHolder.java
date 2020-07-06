package com.example.finance_calc.models.holders;

import com.example.finance_calc.models.FieldsGroup;

public class DepositFieldsGroupHolder {
    private FieldsGroup amountToDepositObj;
    private FieldsGroup depositPeriodObj;
    private FieldsGroup depositPercentObj;
    private FieldsGroup depositIncomeTaxObj;
    private FieldsGroup depositMonthlyReplenishmentObj;

    public DepositFieldsGroupHolder(FieldsGroup amountToDepositObj, FieldsGroup depositPeriodObj, FieldsGroup depositPercentObj, FieldsGroup depositIncomeTaxObj, FieldsGroup depositMonthlyReplenishmentObj) {
        this.amountToDepositObj = amountToDepositObj;
        this.depositPeriodObj = depositPeriodObj;
        this.depositPercentObj = depositPercentObj;
        this.depositIncomeTaxObj = depositIncomeTaxObj;
        this.depositMonthlyReplenishmentObj = depositMonthlyReplenishmentObj;
    }

    public FieldsGroup getAmountToDepositObj() {
        return amountToDepositObj;
    }

    public FieldsGroup getDepositPeriodObj() {
        return depositPeriodObj;
    }

    public FieldsGroup getDepositPercentObj() {
        return depositPercentObj;
    }

    public FieldsGroup getDepositIncomeTaxObj() {
        return depositIncomeTaxObj;
    }

    public FieldsGroup getDepositMonthlyReplenishmentObj() {
        return depositMonthlyReplenishmentObj;
    }
}

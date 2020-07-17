package com.example.finance_calc.models.holders;

import com.example.finance_calc.models.FieldsGroup;

final public class CreditFieldsGroupHolder {

    private FieldsGroup amountToCreditObj;
    private FieldsGroup annualRateCreditObj;
    private FieldsGroup creditPeriodObj;
    private FieldsGroup creditInsurancePercentObj;
    private FieldsGroup creditAdvancePaymentObj;
    private FieldsGroup creditMonthlyTaxObj;

    public CreditFieldsGroupHolder(FieldsGroup amountToCreditObj, FieldsGroup annualRateCreditObj, FieldsGroup creditPeriodObj, FieldsGroup creditInsurancePercentObj, FieldsGroup creditAdvancePaymentObj, FieldsGroup creditMonthlyTaxObj) {
        this.amountToCreditObj = amountToCreditObj;
        this.annualRateCreditObj = annualRateCreditObj;
        this.creditPeriodObj = creditPeriodObj;
        this.creditInsurancePercentObj = creditInsurancePercentObj;
        this.creditAdvancePaymentObj = creditAdvancePaymentObj;
        this.creditMonthlyTaxObj = creditMonthlyTaxObj;
    }

    public FieldsGroup getAmountToCreditObj() {
        return amountToCreditObj;
    }

    public FieldsGroup getAnnualRateCreditObj() {
        return annualRateCreditObj;
    }

    public FieldsGroup getCreditPeriodObj() {
        return creditPeriodObj;
    }

    public FieldsGroup getCreditInsurancePercentObj() {
        return creditInsurancePercentObj;
    }

    public FieldsGroup getCreditAdvancePaymentObj() {
        return creditAdvancePaymentObj;
    }

    public FieldsGroup getCreditMonthlyTaxObj() {
        return creditMonthlyTaxObj;
    }
}

package com.example.finance_calc.managers;

import android.content.Context;

import com.example.finance_calc.models.holders.CreditFieldsGroupHolder;
import com.example.finance_calc.models.holders.CreditTextViewHolder;
import com.example.finance_calc.utils.Calculator;
import com.example.finance_calc.utils.OpMessages;

public class CreditComputing extends Computing {

    private CreditFieldsGroupHolder fieldsGroupHolder;
    private CreditTextViewHolder textViewHolder;

    public CreditComputing(Context context, CreditFieldsGroupHolder fieldsGroupHolder, CreditTextViewHolder textViewHolder) {
        super(context);
        this.fieldsGroupHolder = fieldsGroupHolder;
        this.textViewHolder = textViewHolder;
    }

    //main credit computing method
    public void computeAll() {

        //if any errors from fields data then it does calculation with these values or print error and set output view fields as default value
        if (!isErrorInEditTextField()) {
            int amountToCredit = fieldsGroupHolder.getAmountToCreditObj().getIntFromField();
            float annualRateCredit = fieldsGroupHolder.getAnnualRateCreditObj().getFloatFromField();
            int creditPeriod = fieldsGroupHolder.getCreditPeriodObj().getIntFromField();
            float creditInsurancePercent = fieldsGroupHolder.getCreditInsurancePercentObj().getFloatFromField();
            float creditAdvancePayment = fieldsGroupHolder.getCreditAdvancePaymentObj().getFloatFromField();
            float creditMonthlyTax = fieldsGroupHolder.getCreditMonthlyTaxObj().getFloatFromField();

            float anInitialFee = Calculator.calcAnInitialFee(amountToCredit, creditAdvancePayment);
            float monthlyPayment = Calculator.calcMonthlyPayment(amountToCredit, anInitialFee, creditPeriod, annualRateCredit, creditInsurancePercent, creditMonthlyTax);
            float overpayment = Calculator.calcOverpayment(monthlyPayment, creditPeriod, amountToCredit, anInitialFee);
            float totalRepaymentAmount = Calculator.calcTotalRepaymentAmount(amountToCredit, overpayment);

            //set view fields with calculated values
            setTextView(textViewHolder.getViewAnInitialFee(), anInitialFee);
            setTextView(textViewHolder.getViewMonthlyPayment(), monthlyPayment);
            setTextView(textViewHolder.getViewOverpayment(), overpayment);
            setTextView(textViewHolder.getViewTotalRepaymentAmount(), totalRepaymentAmount);
            //print error message if operation is impossible
            maybeCreatedShowDialogError();
        } else {
            //set view fields as default value
            setTextViewsAsDefault();
        }
    }

    //set default value for all views
    @Override
    protected void setTextViewsAsDefault() {
        textViewHolder.getViewAnInitialFee().setText(OpMessages.DEFAULT_EMPTY_OUTPUT_VIEW_FIELD);
        textViewHolder.getViewMonthlyPayment().setText(OpMessages.DEFAULT_EMPTY_OUTPUT_VIEW_FIELD);
        textViewHolder.getViewOverpayment().setText(OpMessages.DEFAULT_EMPTY_OUTPUT_VIEW_FIELD);
        textViewHolder.getViewTotalRepaymentAmount().setText(OpMessages.DEFAULT_EMPTY_OUTPUT_VIEW_FIELD);
    }

    //if it is one or more error(s) from inputs which user wrote, then return true
    @Override
    protected boolean isErrorInEditTextField() {
        boolean isError = false;
        if (fieldsGroupHolder.getAmountToCreditObj().isErrorAndShow()
                || fieldsGroupHolder.getAnnualRateCreditObj().isErrorAndShow()
                || fieldsGroupHolder.getCreditAdvancePaymentObj().isErrorAndShow()
                || fieldsGroupHolder.getCreditInsurancePercentObj().isErrorAndShow()
                || fieldsGroupHolder.getCreditMonthlyTaxObj().isErrorAndShow()
                || fieldsGroupHolder.getCreditPeriodObj().isErrorAndShow()
        ) {
            isError = true;
        }
        return isError;
    }
}

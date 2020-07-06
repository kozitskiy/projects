package com.example.finance_calc.managers;

import android.content.Context;
import android.widget.CheckBox;

import com.example.finance_calc.models.holders.DepositFieldsGroupHolder;
import com.example.finance_calc.models.holders.DepositTextViewHolder;
import com.example.finance_calc.utils.Calculator;
import com.example.finance_calc.utils.OpMessages;

public class DepositComputing extends Computing {

    private CheckBox capitalizationCheckBox;
    private DepositTextViewHolder textViewHolder;
    private DepositFieldsGroupHolder fieldsGroupHolder;

    public DepositComputing(Context context, CheckBox capitalizationCheckBox, DepositTextViewHolder textViewHolder, DepositFieldsGroupHolder fieldsGroupHolder) {
        super(context);
        this.capitalizationCheckBox = capitalizationCheckBox;
        this.textViewHolder = textViewHolder;
        this.fieldsGroupHolder = fieldsGroupHolder;
    }

    //main credit computing method
    public void computeAll() {

        //if any errors from fields data then it does calculation with these values or print error and set output view fields as default value
        if (!isErrorInEditTextField()) {
            int amountToDeposit = fieldsGroupHolder.getAmountToDepositObj().getIntFromField();
            float depositPercent = fieldsGroupHolder.getDepositPercentObj().getFloatFromField();
            int depositPeriod = fieldsGroupHolder.getDepositPeriodObj().getIntFromField();
            float depositIncomeTax = fieldsGroupHolder.getDepositIncomeTaxObj().getFloatFromField();
            int depositMonthlyReplenishment = fieldsGroupHolder.getDepositMonthlyReplenishmentObj().getIntFromField();

            //if CheckBox is checked or not, then calculate all and set view fields with calculated values
            if (capitalizationCheckBox.isChecked()) {
                textViewHolder.getViewMonthlyIncome().setText(OpMessages.NOT_ACCESSED_AFTER_CAPITAL_CHECKBOX);
                float depositIncomeByAllMonth = Calculator.compoundInterestCalculation(amountToDeposit, depositPeriod, depositPercent, depositIncomeTax, depositMonthlyReplenishment);
                int replenishmentAmountByAllDepositPeriod = Calculator.calcReplenishmentAmountByAllDepositPeriod(depositMonthlyReplenishment, depositPeriod);
                float allDepositPeriodIncome = Calculator.calcAllDepositPeriodIncomeWithCapitalization(depositIncomeByAllMonth, amountToDeposit, replenishmentAmountByAllDepositPeriod, depositPeriod);
                setTextView(textViewHolder.getViewDepositIncomeByAllMonth(), depositIncomeByAllMonth);
                setTextView(textViewHolder.getViewReplenishmentAmountByAllDepositPeriod(), replenishmentAmountByAllDepositPeriod);
                setTextView(textViewHolder.getViewAllDepositPeriodIncome(), allDepositPeriodIncome);
            } else {
                float monthlyIncome = Calculator.calcMonthlyIncome(amountToDeposit, depositPercent, depositPeriod, depositIncomeTax);
                int replenishmentAmountByAllDepositPeriod = Calculator.calcReplenishmentAmountByAllDepositPeriod(depositMonthlyReplenishment, depositPeriod);
                float depositIncomeByAllMonth = Calculator.calcDepositIncomeByAllMonth(monthlyIncome, depositPeriod, replenishmentAmountByAllDepositPeriod, depositPercent);
                float allDepositPeriodIncome = Calculator.calcAllDepositPeriodIncome(monthlyIncome, amountToDeposit, depositPeriod, depositIncomeTax, replenishmentAmountByAllDepositPeriod);
                setTextView(textViewHolder.getViewMonthlyIncome(), monthlyIncome);
                setTextView(textViewHolder.getViewDepositIncomeByAllMonth(), depositIncomeByAllMonth);
                setTextView(textViewHolder.getViewReplenishmentAmountByAllDepositPeriod(), replenishmentAmountByAllDepositPeriod);
                setTextView(textViewHolder.getViewAllDepositPeriodIncome(), allDepositPeriodIncome);
            }
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
        textViewHolder.getViewMonthlyIncome().setText(OpMessages.DEFAULT_EMPTY_OUTPUT_VIEW_FIELD);
        textViewHolder.getViewAllDepositPeriodIncome().setText(OpMessages.DEFAULT_EMPTY_OUTPUT_VIEW_FIELD);
        textViewHolder.getViewDepositIncomeByAllMonth().setText(OpMessages.DEFAULT_EMPTY_OUTPUT_VIEW_FIELD);
        textViewHolder.getViewReplenishmentAmountByAllDepositPeriod().setText(OpMessages.DEFAULT_EMPTY_OUTPUT_VIEW_FIELD);
    }

    //if it is one or more error(s) from inputs which user wrote, then return true
    @Override
    protected boolean isErrorInEditTextField() {
        boolean isError = false;
        if (fieldsGroupHolder.getDepositMonthlyReplenishmentObj().isErrorAndShow()
                || fieldsGroupHolder.getDepositIncomeTaxObj().isErrorAndShow()
                || fieldsGroupHolder.getDepositPercentObj().isErrorAndShow()
                || fieldsGroupHolder.getDepositPeriodObj().isErrorAndShow()
                || fieldsGroupHolder.getAmountToDepositObj().isErrorAndShow()
        ) {
            isError = true;
        }
        return isError;
    }
}

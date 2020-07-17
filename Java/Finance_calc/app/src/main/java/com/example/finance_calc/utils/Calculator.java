package com.example.finance_calc.utils;
//Deposit and Credit calculations with math formulas
public final class Calculator {
    private Calculator() {
    }

    public static float calcMonthlyIncome(int amountToDeposit, float depositPercent, int monthOfDeposit, float depositIncomeTax) {
        float result = amountToDeposit * (depositPercent / 100) / monthOfDeposit;
        return result - result * (depositIncomeTax / 100);
    }

    public static float calcAllDepositPeriodIncome(float monthlyIncome, int amountToDeposit, int monthOfDeposit, float depositIncomeTax, int replenishmentAmountByAllDepositPeriod) {
        float result = monthlyIncome * monthOfDeposit + amountToDeposit;
        return result - result * (depositIncomeTax / 100) + replenishmentAmountByAllDepositPeriod;
    }

    public static float compoundInterestCalculation(int amountToDeposit, int monthOfDeposit, float depositPercent, float depositIncomeTax, int depositMonthlyReplenishment) {
        float extentOfFormula = (float) (monthOfDeposit / 12) * 12;
        float secondStepOfFormula = 1 + ((depositPercent / 100) / 12);
        float thirdStepOfFormula = (float) Math.pow(secondStepOfFormula, extentOfFormula);
        float amountWithReplenishment = amountToDeposit + (depositMonthlyReplenishment);
        float result = (amountWithReplenishment * thirdStepOfFormula) - amountToDeposit;
        return result - result * (depositIncomeTax / 100);
    }

    public static float calcAllDepositPeriodIncomeWithCapitalization(float depositIncomeByAllMonth, int amountToDeposit, int replenishmentAmountByAllDepositPeriod, int depositPeriod) {
        if (depositPeriod < 12) {
            return depositIncomeByAllMonth + amountToDeposit;
        } else {
            return depositIncomeByAllMonth + amountToDeposit + replenishmentAmountByAllDepositPeriod;
        }
    }

    public static float calcDepositIncomeByAllMonth(float monthlyIncome, int depositPeriod, int replenishmentAmountByAllDepositPeriod, float depositPercent) {
        float result = monthlyIncome * depositPeriod;
        return result + replenishmentAmountByAllDepositPeriod * (depositPercent / 100);
    }

    public static int calcReplenishmentAmountByAllDepositPeriod(int depositMonthlyReplenishment, int depositPeriod) {
        return depositMonthlyReplenishment * depositPeriod;
    }

    public static float calcAnInitialFee(int amountToCredit, float creditAdvancePayment) {
        return amountToCredit * (creditAdvancePayment / 100);
    }

    public static float calcMonthlyPayment(int amountToCredit, float anInitialFee, int creditPeriod, float annualRateCredit, float creditInsurancePercent, float creditMonthlyTax) {
        float loanBodyWithoutAdvancePayment = amountToCredit - anInitialFee;
        float monthlyAmountCredit = loanBodyWithoutAdvancePayment / creditPeriod;
        float monthlyAmountCreditWithRate = (loanBodyWithoutAdvancePayment * (annualRateCredit / 100)) / creditPeriod;
        float monthlyAmountCreditWithInsurance = monthlyAmountCredit * (creditInsurancePercent / 100);
        float monthlyAmountCreditWithTax = monthlyAmountCredit * (creditMonthlyTax / 100);
        return monthlyAmountCredit + monthlyAmountCreditWithRate + monthlyAmountCreditWithInsurance + monthlyAmountCreditWithTax;
    }

    public static float calcOverpayment(float monthlyPayment, int creditPeriod, int amountToCredit, float anInitialFee) {
        return (monthlyPayment * creditPeriod) - amountToCredit + anInitialFee;
    }

    public static float calcTotalRepaymentAmount(int amountToCredit, float overpayment) {
        return amountToCredit + overpayment;
    }
}

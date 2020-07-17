package com.example.finance_calc.utils;

import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.example.finance_calc.listeners.InputFocusChangeListener;
import com.example.finance_calc.listeners.InputTextWatcher;
import com.example.finance_calc.managers.CreditComputing;
import com.example.finance_calc.managers.DepositComputing;
import com.example.finance_calc.models.FieldsGroup;
import com.example.finance_calc.models.holders.CreditFieldsGroupHolder;
import com.example.finance_calc.models.holders.DepositFieldsGroupHolder;

public final class HelpUtils {
    private HelpUtils() {
    }

    public static void setListenersForCreditFields(CreditComputing creditComputing, CreditFieldsGroupHolder fieldsGroupHolder) {
        FieldsGroup fieldsGroup;
        fieldsGroup = fieldsGroupHolder.getCreditPeriodObj();
        fieldsGroup.getEditText().addTextChangedListener(new InputTextWatcher(fieldsGroup, creditComputing));
        fieldsGroup.getEditText().setOnFocusChangeListener(new InputFocusChangeListener(fieldsGroup));

        fieldsGroup = fieldsGroupHolder.getAnnualRateCreditObj();
        fieldsGroup.getEditText().addTextChangedListener(new InputTextWatcher(fieldsGroup, creditComputing));
        fieldsGroup.getEditText().setOnFocusChangeListener(new InputFocusChangeListener(fieldsGroup));

        fieldsGroup = fieldsGroupHolder.getCreditAdvancePaymentObj();
        fieldsGroup.getEditText().addTextChangedListener(new InputTextWatcher(fieldsGroup, creditComputing));
        fieldsGroup.getEditText().setOnFocusChangeListener(new InputFocusChangeListener(fieldsGroup));

        fieldsGroup = fieldsGroupHolder.getCreditInsurancePercentObj();
        fieldsGroup.getEditText().addTextChangedListener(new InputTextWatcher(fieldsGroup, creditComputing));
        fieldsGroup.getEditText().setOnFocusChangeListener(new InputFocusChangeListener(fieldsGroup));

        fieldsGroup = fieldsGroupHolder.getCreditMonthlyTaxObj();
        fieldsGroup.getEditText().addTextChangedListener(new InputTextWatcher(fieldsGroup, creditComputing));
        fieldsGroup.getEditText().setOnFocusChangeListener(new InputFocusChangeListener(fieldsGroup));

        fieldsGroup = fieldsGroupHolder.getAmountToCreditObj();
        fieldsGroup.getEditText().addTextChangedListener(new InputTextWatcher(fieldsGroup, creditComputing));
        fieldsGroup.getEditText().setOnFocusChangeListener(new InputFocusChangeListener(fieldsGroup));
    }

    public static void setListenersForDepositFields(final DepositComputing depositComputing, DepositFieldsGroupHolder fieldsGroupHolder, CheckBox capitalizationCheckBox) {
        FieldsGroup fieldsGroup;
        fieldsGroup = fieldsGroupHolder.getAmountToDepositObj();
        fieldsGroup.getEditText().addTextChangedListener(new InputTextWatcher(fieldsGroup, depositComputing));
        fieldsGroup.getEditText().setOnFocusChangeListener(new InputFocusChangeListener(fieldsGroup));

        fieldsGroup = fieldsGroupHolder.getDepositIncomeTaxObj();
        fieldsGroup.getEditText().addTextChangedListener(new InputTextWatcher(fieldsGroup, depositComputing));
        fieldsGroup.getEditText().setOnFocusChangeListener(new InputFocusChangeListener(fieldsGroup));

        fieldsGroup = fieldsGroupHolder.getDepositMonthlyReplenishmentObj();
        fieldsGroup.getEditText().addTextChangedListener(new InputTextWatcher(fieldsGroup, depositComputing));
        fieldsGroup.getEditText().setOnFocusChangeListener(new InputFocusChangeListener(fieldsGroup));

        fieldsGroup = fieldsGroupHolder.getDepositPercentObj();
        fieldsGroup.getEditText().addTextChangedListener(new InputTextWatcher(fieldsGroup, depositComputing));
        fieldsGroup.getEditText().setOnFocusChangeListener(new InputFocusChangeListener(fieldsGroup));

        fieldsGroup = fieldsGroupHolder.getDepositPeriodObj();
        fieldsGroup.getEditText().addTextChangedListener(new InputTextWatcher(fieldsGroup, depositComputing));
        fieldsGroup.getEditText().setOnFocusChangeListener(new InputFocusChangeListener(fieldsGroup));

        //add listener on capitalization checkbox
        capitalizationCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                depositComputing.computeAll();
            }
        });
    }
}

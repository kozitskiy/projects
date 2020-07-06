package com.example.finance_calc.listeners;

import android.view.View;

import com.example.finance_calc.models.FieldsGroup;

public class InputFocusChangeListener implements View.OnFocusChangeListener {

    private FieldsGroup fieldsGroup;

    public InputFocusChangeListener(FieldsGroup fieldsGroup) {
        this.fieldsGroup = fieldsGroup;
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (!hasFocus) {
            //replace dot if it is in the end of value
            fieldsGroup.replaceEndingWithDot();
        }
    }
}

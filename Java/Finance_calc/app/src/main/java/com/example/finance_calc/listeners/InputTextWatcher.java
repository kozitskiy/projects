package com.example.finance_calc.listeners;

import android.text.Editable;
import android.text.TextWatcher;

import com.example.finance_calc.managers.Computing;
import com.example.finance_calc.models.FieldsGroup;

public class InputTextWatcher implements TextWatcher {

    private FieldsGroup fieldsGroup;
    private Computing computing;

    public InputTextWatcher(FieldsGroup fieldsGroup, Computing computing) {
        this.fieldsGroup = fieldsGroup;
        this.computing = computing;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        //nothing to do
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        //nothing to do
    }

    @Override
    public void afterTextChanged(Editable s) {
        //replace wrong cases which has written and only after that computing
        fieldsGroup.disableMoreTwoNumAfterDot();
        fieldsGroup.disableSeveralZeros();
        fieldsGroup.disableStartingWithDot();
        computing.computeAll();

    }
}

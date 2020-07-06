package com.example.finance_calc.models;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class FieldsGroup {
    private static final int COUNT_OF_NUMBERS_AFTER_DOT_INPUT = 2;

    private EditText editText;
    private TextView textView = null;

    //define 2 constructors for different types of object that we need
    public FieldsGroup(EditText editText) {
        this.editText = editText;
    }

    public FieldsGroup(EditText editText, TextView textView) {
        this.editText = editText;
        this.textView = textView;
    }

    //define getters
    public EditText getEditText() {
        return editText;
    }

    //getting text from EditText field
    private String getTextFromField() {
        return editText.getText().toString();
    }

    //getting field text length
    private int getFieldLength() {
        return editText.getText().length();
    }

    //getting float value from number field
    public float getFloatFromField() {
        return !getTextFromField().isEmpty() ? Float.parseFloat(getTextFromField()) : 0;
    }

    //getting int value from number field
    public int getIntFromField() {
        return !getTextFromField().isEmpty() ? Math.round(Float.parseFloat(getTextFromField())) : 0;
    }

    //checking that last char in EditText is "."
    private boolean isDotLastChar() {
        return getTextFromField().length() >= 1 && getTextFromField().charAt(getTextFromField().length() - 1) == '.';
    }

    //disable enter several zeros into EditText field
    public void disableSeveralZeros() {
        if (getFieldLength() >= 2 && getTextFromField().charAt(0) == '0' && getTextFromField().charAt(1) != '.')
            getEditText().setText(String.valueOf(getTextFromField().charAt(getFieldLength() - 1)));
        getEditText().setSelection(getFieldLength());
    }

    //disable start entering with "." into EditText field
    public void disableStartingWithDot() {
        if (getFieldLength() == 1 && getTextFromField().charAt(0) == '.')
            getEditText().setText("");
    }

    //replace char "." to "" if it is last char in the EditText field
    public void replaceEndingWithDot() {
        if (getFieldLength() >= 1 && getTextFromField().charAt(getFieldLength() - 1) == '.')
            getEditText().setText(getTextFromField().replaceFirst("\\.", ""));
    }

    //disable entering more then 2 chars after dot. It is replacing with previous entered string if chars after dot is more then 2
    public void disableMoreTwoNumAfterDot() {
        try {
            int decimalLen = getTextFromField().split("\\.")[1].length();
            if (decimalLen > COUNT_OF_NUMBERS_AFTER_DOT_INPUT) {
                getEditText().setText(getTextFromField().substring(0, getFieldLength() - 1));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    //validation that can't be empty and zero number and dot is last symbol
    public boolean isErrorAndShow() {
        boolean hasError = false;
        if (textView != null) {
            if (getTextFromField().isEmpty() || isDotLastChar() || getIntFromField() <= 0) {
                hasError = true;
                textView.setVisibility(View.VISIBLE);
            } else {
                textView.setVisibility(View.INVISIBLE);
            }
        }

        return hasError;
    }
}

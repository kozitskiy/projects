package com.example.finance_calc.managers;

import android.content.Context;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import com.example.finance_calc.utils.OpMessages;

import java.text.DecimalFormat;

public abstract class Computing {

    private static final String DECIMAL_FORMAT_PATTERN = "###,###.##";
    private static final String CURRENCY_NAME = "грн";
    protected static boolean isWrongCalculatedValue = false;

    Context context;

    public Computing(Context context) {
        this.context = context;
    }

    public abstract void computeAll();
    protected abstract void setTextViewsAsDefault();
    protected abstract boolean isErrorInEditTextField();

    //formatting value with pattern
    protected String getFormattedStrValue(float value) {
        DecimalFormat formatter = new DecimalFormat(DECIMAL_FORMAT_PATTERN);
        return formatter.format(value);
    }

    //set text in view that we received
    protected void setTextView(TextView textView, float value) {
        if (!isUnavailableResultValue(value)) {
            textView.setText(String.format("%s %s", getFormattedStrValue(value), CURRENCY_NAME));
        } else {
            isWrongCalculatedValue = true;
        }
    }

    //checking received result amount after calculation
    private boolean isUnavailableResultValue(Float value) {
        return value.isNaN() || value.isInfinite() || value < 0;
    }

    //if calculated value is wrong then create error modal dialog and show it
    protected void maybeCreatedShowDialogError() {
        if (isWrongCalculatedValue) {
            setTextViewsAsDefault();
            new AlertDialog.Builder(context)
                    .setTitle(OpMessages.DIALOG_ERROR_TITLE)
                    .setMessage(OpMessages.DIALOG_ERROR_MSG)
                    .setPositiveButton(OpMessages.DIALOG_ERROR_BTN_NAME, null)
                    .setCancelable(false)
                    .create().show();
            isWrongCalculatedValue = false;
        }
    }
}
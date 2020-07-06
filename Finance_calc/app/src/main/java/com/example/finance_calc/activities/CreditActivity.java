package com.example.finance_calc.activities;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.finance_calc.managers.CreditComputing;
import com.example.finance_calc.models.FieldsGroup;
import com.example.finance_calc.models.holders.CreditFieldsGroupHolder;
import com.example.finance_calc.models.holders.CreditTextViewHolder;
import com.example.finance_calc.utils.HelpUtils;
import com.example.first_hackathon.R;

public class CreditActivity extends AppCompatActivity {
    private CreditComputing creditComputing;
    private CreditTextViewHolder textViewHolder;
    private CreditFieldsGroupHolder fieldsGroupHolder;

    @SuppressLint("CutPasteId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credit);


        //putting TextView objects into HashMap collection with defined key
        textViewHolder = new CreditTextViewHolder(
                (TextView) findViewById(R.id.txt_credit_first_payment),
                (TextView) findViewById(R.id.txt_credit_monthly_payment),
                (TextView) findViewById(R.id.txt_credit_overpayment),
                (TextView) findViewById(R.id.txt_credit_total_repayment_sum)
        );


        //putting both EditText and TextView objects into HashMap collection with defined key
        fieldsGroupHolder = new CreditFieldsGroupHolder(
                new FieldsGroup((EditText) findViewById(R.id.edt_credit_sum), (TextView) findViewById(R.id.txt_credit_sum_error)),
                new FieldsGroup((EditText) findViewById(R.id.edt_credit_rate), (TextView) findViewById(R.id.txt_credit_rate_error)),
                new FieldsGroup((EditText) findViewById(R.id.edt_credit_period), (TextView) findViewById(R.id.txt_credit_period_error)),
                new FieldsGroup((EditText) findViewById(R.id.edt_credit_insurance)),
                new FieldsGroup((EditText) findViewById(R.id.edt_credit_prepayment)),
                new FieldsGroup((EditText) findViewById(R.id.edt_credit_commission))
        );

        //creating CreditComputing object with created data
        creditComputing = new CreditComputing(CreditActivity.this, fieldsGroupHolder, textViewHolder);

        //add listener to EditText on change focus from it
        HelpUtils.setListenersForCreditFields(creditComputing, fieldsGroupHolder);
    }
}


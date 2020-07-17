package com.example.finance_calc.activities;

import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.finance_calc.managers.DepositComputing;
import com.example.finance_calc.models.FieldsGroup;
import com.example.finance_calc.models.holders.DepositFieldsGroupHolder;
import com.example.finance_calc.models.holders.DepositTextViewHolder;
import com.example.finance_calc.utils.HelpUtils;
import com.example.first_hackathon.R;

public class DepositActivity extends AppCompatActivity {

    private DepositComputing depositComputing;
    private DepositTextViewHolder textViewHolder;
    private DepositFieldsGroupHolder fieldsGroupHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deposit);

        //putting TextView objects into DepositTextViewHolder
        textViewHolder = new DepositTextViewHolder(
                (TextView) findViewById(R.id.txt_deposit_monthly_income),
                (TextView) findViewById(R.id.txt_deposit_total_income_through_months),
                (TextView) findViewById(R.id.txt_deposit_total_replenishment),
                (TextView) findViewById(R.id.txt_deposit_total_sum)
        );

        //putting both EditText and TextView objects into DepositFieldsGroupHolder
        fieldsGroupHolder = new DepositFieldsGroupHolder(
                new FieldsGroup((EditText) findViewById(R.id.edt_deposit_sum), (TextView) findViewById(R.id.txt_deposit_sum_error)),
                new FieldsGroup((EditText) findViewById(R.id.edt_deposit_period), (TextView) findViewById(R.id.txt_deposit_period_error)),
                new FieldsGroup((EditText) findViewById(R.id.edt_deposit_rate), (TextView) findViewById(R.id.txt_deposit_rate_error)),
                new FieldsGroup((EditText) findViewById(R.id.edt_deposit_tax)),
                new FieldsGroup((EditText) findViewById(R.id.edt_deposit_replenishment))
        );

        //getting CheckBox objects
        CheckBox capitalizationCheckBox = findViewById(R.id.chk_capitalization);

        //creating DepositComputing object with created data
        depositComputing = new DepositComputing(DepositActivity.this, capitalizationCheckBox, textViewHolder, fieldsGroupHolder);

        //add listener to each EditText elements (on change focus and on change them)
        HelpUtils.setListenersForDepositFields(depositComputing, fieldsGroupHolder, capitalizationCheckBox);
    }
}


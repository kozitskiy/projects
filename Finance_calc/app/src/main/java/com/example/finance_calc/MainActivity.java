package com.example.finance_calc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.first_hackathon.R;
import com.example.finance_calc.activities.CreditActivity;
import com.example.finance_calc.activities.DepositActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //get main buttons
        Button btnDeposit = findViewById(R.id.btn_deposit);
        Button btnCredit = findViewById(R.id.btn_credit);

        //add click listeners to buttons
        btnDeposit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), DepositActivity.class);
                startActivity(intent);
            }
        });

        btnCredit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CreditActivity.class);
                startActivity(intent);
            }
        });
    }
}



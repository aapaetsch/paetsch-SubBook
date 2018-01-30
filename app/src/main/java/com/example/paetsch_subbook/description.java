package com.example.paetsch_subbook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class description extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);

        EditText editText = (EditText) findViewById(R.id.subName);
        EditText editText1 = (EditText) findViewById(R.id.subDate);
        EditText editText2 = (EditText) findViewById(R.id.subCharge);
        EditText editText3 = (EditText) findViewById(R.id.subComment);

        String subscriptionName = editText.getText().toString();
        String subscriptionDate = editText1.getText().toString();
        String subscriptionCharge = editText2.getText().toString();
        String subscriptionComment = editText3.getText().toString();





    }
}

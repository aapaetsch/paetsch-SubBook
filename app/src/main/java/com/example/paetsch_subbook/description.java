package com.example.paetsch_subbook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class description extends AppCompatActivity {
    private String subscriptionName;
    private String subscriptionDate;
    private float subscriptionCharge;
    private String subscriptionComment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);


        Button newsub = (Button) findViewById(R.id.add);

        TextView sName = (TextView) findViewById(R.id.subName);
        TextView sDate = (TextView) findViewById(R.id.subDate);
        TextView sCharge = (TextView) findViewById(R.id.subCharge);
        TextView sComment = (TextView) findViewById(R.id.subComment);

        newsub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Todo: do some checks then add to subs list
                setContentView(R.layout.activity_main);//causes crash
            }
        });





    }

}

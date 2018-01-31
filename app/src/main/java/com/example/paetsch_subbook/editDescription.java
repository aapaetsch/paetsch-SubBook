package com.example.paetsch_subbook;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by aapae on 1/30/2018.
 */

public class editDescription extends AppCompatActivity{
    private int pos;
    private Subscription subscription;
    private Button delButton;
    private Button addButton;
    private String subscriptionName;
    private String subscriptionDate;
    private String subscriptionComment;
    private float subscriptionCharge;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);

        Intent intent = getIntent();
        pos = Integer.parseInt(intent.getStringExtra("Subscription_Position"));
        subscription = MainActivity.subscriptions.get(pos);

        delButton = (Button) findViewById(R.id.del);
        delButton.setVisibility(View.VISIBLE);
        addButton = (Button) findViewById(R.id.add);
        addButton.setText("Save");

        TextView sName = (TextView) findViewById(R.id.subName);
        TextView sDate = (TextView) findViewById(R.id.subDate);
        TextView sCharge = (TextView) findViewById(R.id.subCharge);
        TextView sComment = (TextView) findViewById(R.id.subComment);

        sName.setText(MainActivity.subscriptions.get(pos).getSubscriptionName());
        sDate.setText(MainActivity.subscriptions.get(pos).getSubscriptionDate());
        sCharge.setText(String.valueOf(MainActivity.subscriptions.get(pos).getSubscriptionCharge()));
        sComment.setText(MainActivity.subscriptions.get(pos).getSubscriptionComment());






        addButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //Todo: include something to add a subscription

            }
        });

        delButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //Todo: include something to remove a subscription
            }
        });
    }
}

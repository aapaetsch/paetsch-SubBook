package com.example.paetsch_subbook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class description extends AppCompatActivity {
    private String subscriptionName;
    private String subscriptionDate;
    private String subscriptionCharge;
    private String subscriptionComment;
    private TextView sName;
    private TextView sDate;
    private TextView sCharge;
    private TextView sComment;
    private TextView ptext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);

        Button newsub = (Button) findViewById(R.id.add);

        sName = (TextView) findViewById(R.id.subName);
        sDate = (TextView) findViewById(R.id.subDate);
        sCharge = (TextView) findViewById(R.id.subCharge);
        sComment = (TextView) findViewById(R.id.subComment);

        newsub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subscriptionDate = sDate.getText().toString();
                subscriptionName = sName.getText().toString();
                subscriptionCharge = sCharge.getText().toString();
                subscriptionComment = validComment(sComment.getText().toString());
                if(validDate(subscriptionDate) == 0|| validName(subscriptionName) == 0||validCharge(subscriptionCharge)==0) {
                    String poptext = "";
                    if(validName(subscriptionName) == 0){
                        poptext = "Name field cannot be left blank\n";
                    }
                    if(validDate(subscriptionDate) == 0){
                        if (poptext == null){
                            poptext = "Format of Date is incorrect, should be yyyy-mm-dd\n";
                        }
                        else{
                            poptext = poptext + "\nFormat of Date is incorrect, should be yyyy-mm-dd\n";
                        }
                    }
                    if(validCharge(subscriptionCharge)==0){
                        if (poptext == null){
                            poptext = "Charge field cannot be left blank.";
                        }
                        else {
                            poptext = poptext + "\nCharge field cannot be left blank.";
                        }
                    }
                    initPop(v,poptext);
                }
                else{
                    Subscription subscription = new Subscription(subscriptionName,subscriptionDate,Float.valueOf(subscriptionCharge),subscriptionComment);
                    MainActivity.subscriptions.add(subscription);
                    MainActivity.subscriptionAdapter.notifyDataSetChanged();
                    MainActivity.saveSubs(description.this);
                    finish();
                }
            }
        });

    }
    private int validDate(String entryDate){
        if (entryDate.length()!= 10){return 0;}
        else{
            SimpleDateFormat simple = new SimpleDateFormat("yyyy-mm-dd");
            simple.setLenient(false);
            try{
                Date validDate = simple.parse(entryDate);
                if (Integer.valueOf(subscriptionDate.substring(5,7))>12||Integer.valueOf(subscriptionDate.substring(5,7))<1){return 0;}
                else if (Integer.valueOf(subscriptionDate.substring(8,10))>31||Integer.valueOf(subscriptionDate.substring(8,10))<1){return 0;}
                else if (Integer.valueOf(subscriptionDate.substring(0,4))<1500){return 0;}
            }
            catch (ParseException e){
                return 0;
            }
            return 1;
        }
    }
    private int validName(String nameSub){
        if (nameSub.length() == 0){
            return 0;
        }
        return 1;
    }
    private int validCharge(String chargeSub){
        if (chargeSub.length() == 0){
            return 0;
        }
        else if (Float.valueOf(chargeSub)<0){
            return 0;
        }
        else{
            return 1;
        }

    }
    private String validComment(String commentSub){
        if (commentSub.length() == 0){
            return " ";
        }
        else{
            return commentSub;
        }
    }
    private void initPop(View v,String poptext){
        //Taken from https://stackoverflow.com/questions/34779466/android-studio-popup-window-layout-with-buttons-onclick

        final LayoutInflater inflater = LayoutInflater.from(description.this);
        final View pop = inflater.inflate(R.layout.popup, null);

        final PopupWindow window = new PopupWindow(pop, 850,680,true);
        window.showAtLocation(v, Gravity.CENTER,0,0);
        window.setAnimationStyle(R.style.Animation);
        ptext = (TextView) pop.findViewById(R.id.popup_text);
        window.setFocusable(true);
        window.update();
        ptext.setText(poptext);
    }
}

package com.example.paetsch_subbook;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by aapae on 1/30/2018.
 * Class adding a new subscription
 */
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
        //on click listener for the add button adding a new subscription
        newsub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // getting the text in the textviews and converting to a stored string
                subscriptionDate = sDate.getText().toString();
                subscriptionName = sName.getText().toString();
                subscriptionCharge = sCharge.getText().toString();
                subscriptionComment = sComment.getText().toString();
                //Checking validity of each entry and updating an error message based on invalid entries
                if(validDate(subscriptionDate) == 0|| validName(subscriptionName) == 0||validCharge(subscriptionCharge)==0) {
                    String poptext = "";
                    if(validName(subscriptionName) == 0){
                        poptext = "Name field cannot be left blank\n";
                    }
                    if(validDate(subscriptionDate) == 0){
                        if (poptext == ""){
                            poptext = "Format of Date is incorrect, should be yyyy-mm-dd\n";
                        }
                        else{
                            poptext = poptext + "\nFormat of Date is incorrect, should be yyyy-mm-dd\n";
                        }
                    }
                    if(validCharge(subscriptionCharge)==0){
                        if (poptext == ""){
                            poptext = "Charge field cannot be left blank.";
                        }
                        else {
                            poptext = poptext + "\nCharge field cannot be left blank.";
                        }
                    }
                    //method call for creation of a popup with error message, clickable anywhere on screen to dismiss
                    initPop(v,poptext);
                }
                else{
                    //Creation and saving a new subscription
                    Subscription subscription = new Subscription(subscriptionName,subscriptionDate,Float.valueOf(subscriptionCharge),subscriptionComment);
                    MainActivity.subscriptions.add(subscription);
                    MainActivity.subscriptionAdapter.notifyDataSetChanged();
                    MainActivity.saveSubs(description.this);
                    finish();//returning to the main activity
                }
            }
        });

    }
    /**
     * method for checking the validity of the format as well as valid month and day entries
     * from an input string of a date
     * @param entryDate
     * String of the entered date
     * @return
     * Returns a 1 or 0 for correct or incorrect entry
     */
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

    /**
     * Checks validity of a subscription name entry
     * @param nameSub
     * input string of the subscription name being validated
     * @return
     * returns 1 or 0 based on validity of the input
     */
    private int validName(String nameSub){
        if (nameSub.length() == 0){
            return 0;
        }
        return 1;
    }
    /**
     * checking validity of a charge entry
     * @param chargeSub
     * string value of the subscription charge
     * @return
     * returns 1 or 0 based on validity of input
     */
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
    /**
     * Method for creating a popup with an error message
     * adapted from https://stackoverflow.com/questions/34779466/android-studio-popup-window-layout-with-buttons-onclick
     * @param v
     * @param poptext
     * String of the error message that is to be displayed in the popup window
     */
    private void initPop(View v,String poptext){

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

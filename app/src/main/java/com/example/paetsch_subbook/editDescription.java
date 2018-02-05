package com.example.paetsch_subbook;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
 * class for editing the contents of a subscription, gets the subscription
 * based on its location in the listview when clicked
 */

public class editDescription extends AppCompatActivity{
    private int pos;
    private Subscription subscription;
    private Button delButton;
    private Button addButton;
    private String subscriptionName;
    private String subscriptionDate;
    private String subscriptionComment;
    private String subscriptionCharge;
    private TextView ptext;
    private TextView sName;
    private TextView sDate;
    private TextView sCharge;
    private TextView sComment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);//Sets the view to the same view as description

        Intent intent = getIntent();
        //pos is required to get the specified subscription to be edited
        pos = Integer.parseInt(intent.getStringExtra(MainActivity.POINTER));
        subscription = MainActivity.subscriptions.get(pos);
        // Buttons to save and delete a subscription
        delButton = (Button) findViewById(R.id.del);
        delButton.setVisibility(View.VISIBLE);
        addButton = (Button) findViewById(R.id.add);
        addButton.setText("Save");
        // gets each text view by ID
        sName = (TextView) findViewById(R.id.subName);
        sDate = (TextView) findViewById(R.id.subDate);
        sCharge = (TextView) findViewById(R.id.subCharge);
        sComment = (TextView) findViewById(R.id.subComment);
        //gets the data of each item in the subscription based on position and sets each corresponding text view to that
        sName.setText(MainActivity.subscriptions.get(pos).getSubscriptionName());
        sDate.setText(MainActivity.subscriptions.get(pos).getSubscriptionDate());
        sCharge.setText(String.valueOf(MainActivity.subscriptions.get(pos).getSubscriptionCharge()));
        sComment.setText(MainActivity.subscriptions.get(pos).getSubscriptionComment());

        // On click listener for the save button
        addButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                // takes the data in a text view and converts to the corresponding string
                subscriptionName = sName.getText().toString();
                subscriptionDate = sDate.getText().toString();
                subscriptionCharge = sCharge.getText().toString();
                subscriptionComment = sComment.getText().toString();
                // check of validity for data entry
                if(validDate(subscriptionDate) == 0|| validName(subscriptionName) == 0||validCharge(subscriptionCharge)==0) {
                    String poptext = "";// string for error message describing each incorrect entry
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
                    // calls method for popup and gives it a view and the error message string
                    initPop(v,poptext);
                }
                else{
                    //If there are no invalid entries, a subscription is updated based off of changes
                    subscription.setCurrentSub(subscriptionName, subscriptionDate,Float.valueOf(subscriptionCharge),subscriptionComment);
                    //notifying the dataset/adapter of the change to the data and updating accordingly
                    MainActivity.subscriptionAdapter.notifyDataSetChanged();
                    MainActivity.saveSubs(editDescription.this);
                    finish();//brings us back to the main activity
                }
            }
        });
        // On click listener for the delete button
        delButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                // removes a subscription based on its position within the array
                MainActivity.subscriptions.remove(pos);
                // notifying the dataset/adapter of the change to the data and updating accordingly
                MainActivity.subscriptionAdapter.notifyDataSetChanged();
                MainActivity.saveSubs(editDescription.this);
                finish();//brings us back to the main activity
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
            //checks for the correct pattern of the date
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


        final LayoutInflater inflater = LayoutInflater.from(editDescription.this);
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

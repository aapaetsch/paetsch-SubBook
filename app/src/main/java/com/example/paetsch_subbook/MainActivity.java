
/*
 * SubBook - Tracking app for various subscriptions
 * Copyright (c) 2018. Aidan Paetsch. CMPUT 301. University of Alberta. All Rights Reserved.
 * You may use, distribute or modify this code under the terms and conditions of the
 * Code of Student Behaviour at the University of Alberta
 * This program comes with ABSOLUTELY NO WARRANTY;
 * <You can find a copy of the license in this project> Otherwise please see <http://www.gnu.org/licenses/>
 */

package com.example.paetsch_subbook;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * @author Aidan Paetsch
 * @date 01/27/2018
 * Build using API 26
 */
public class MainActivity extends AppCompatActivity {
    private ListView myListView;
    private float Total;
    public static ArrayAdapter<Subscription> subscriptionAdapter;
    public static ArrayList<Subscription> subscriptions;
    public static final String FILENAME = "subscriptions.sav";
    public static final String POINTER = "Subscription_positon";

    /**
     * Sets the layout of the app to the main activity when the app loads in
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //gets the custom list view
        myListView = (ListView) findViewById(R.id.subsList);
    }
    /**
     * Calls loadIn method to get saved data, creates a new adapter object and
     *  calculates the total monthly charge of all subscriptions
     */
    protected void onStart(){
        super.onStart();
        loadIn();
        subscriptionAdapter = new CustomAdapter(this, subscriptions);
        myListView.setAdapter(subscriptionAdapter);
        Total = 0; // sets total charges to zero on each on start to calculate if the value has been changed
        for (int i = 0;i < subscriptionAdapter.getCount(); i++){//Goes through all subscriptions and gets their charge value
            Total += Float.valueOf(subscriptions.get(i).getSubscriptionCharge());
        }
        //Setting the textview to display the total charges of all subscriptions
        TextView totalText = (TextView) findViewById(R.id.totalCharge);
        String T = String.valueOf(Total);
        Log.v("string entry",T);
        totalText.setText("$"+String.valueOf(Total));
    }
    /**
     * Load function adapted from loadFromFile function on lonelytwitter
     * loads in subscriptions from json using Gson
     * @see Gson
     */
    private void loadIn(){
        try{
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));

            Gson g = new Gson();

            Type listType = new TypeToken<ArrayList<Subscription>>(){}.getType();
            subscriptions = g.fromJson(in, listType);
        }
        catch(FileNotFoundException e){subscriptions = new ArrayList<Subscription>();}
    }
    /**
     * -save fuction adapted from saveInFile from lonelytwitter
     * -saves subscriptions to json using Gson
     * -Takes in a context so this function can be used to save subscriptions
     *  in other contexts besides MainActivity
     * @see Gson
     * @param context allows this function to be called in other java classes
     */
    public static void saveSubs(Context context){
        try{
            FileOutputStream fos = context.openFileOutput(FILENAME, Context.MODE_PRIVATE);
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));
            Gson g = new Gson();
            g.toJson(subscriptions,out);
            out.flush();
            fos.close();
        }
        catch(FileNotFoundException e){e.printStackTrace();}
        catch(IOException e){e.printStackTrace();}
    }
    /**
     * On click for adding a new subscription
     * @param v takes in view
     */
    public void newSub(View v){
        Intent intent = new Intent(this,description.class);
        startActivity(intent);
    }
}

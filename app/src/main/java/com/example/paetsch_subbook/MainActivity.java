package com.example.paetsch_subbook;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

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
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView myListView;

    public static ArrayAdapter<Subscription> subscriptionAdapter;
    public static ArrayList<Subscription> subscriptions;
    private static final String FILENAME = "subscriptions.sav";
    private static final String POINTER = "Subscription_positon";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myListView = (ListView) findViewById(R.id.subsList);


    }
    protected void onStart(){
        super.onStart();
        loadIn();
        subscriptionAdapter = new CustomAdapter(this, subscriptions);
        myListView.setAdapter(subscriptionAdapter);


    }

    /**
     * Load function adapted from loadFromFile function on lonelytwitter
     * loads in subscriptions from json using Gson
     * @see Gson
     *
     */
    private void loadIn(){
        try{
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            Gson g = new Gson();

            Type listType = new TypeToken<ArrayList<Subscription>>(){}.getType();
            subscriptions = g.fromJson(in, listType);
        }catch(FileNotFoundException e){
            subscriptions = new ArrayList<Subscription>();
        }

    }

    /**
     * save fuction adapted from saveInFile from lonelytwitter
     * saves subscriptions to json using Gson
     * @see Gson
     * @param context
     */
    public static void saveSubs(Context context){
        try{
            FileOutputStream fos = context.openFileOutput(FILENAME, Context.MODE_PRIVATE);
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));
            Gson g = new Gson();
            g.toJson(subscriptions,out);
            out.flush();
            fos.close();
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }

    }


    /**
     * On click for adding a new subscription
     * @param v
     */
    public void newSub(View v){
        Intent intent = new Intent(this,description.class);
        startActivity(intent);
    }



}

package com.example.paetsch_subbook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView myListView;

    public static ArrayList<Subscription> subscriptions = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myListView = (ListView) findViewById(R.id.subsList);


//        ListView list_view = (ListView) findViewById(R.id.subsList);

//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, subs);
//        list_view.setAdapter(adapter);
//        list_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//            }
//        });
    }




    public void newSub(View v){
        Intent intent = new Intent(this,description.class);
        startActivity(intent);
    }


// unsure as to if this is necessary
//    public void chooseASubscription(View v){
//        Toast.makeText(this,"Choose a subscription",Toast.LENGTH_SHORT).show();
//        SubscriptionListController subControl = new SubscriptionListController();
//        try{
//            Subscription s = subControl.chooseSubscription();
//        }catch(EmptySubscriptionListException e){
//            Toast.makeText(this, "There are no subscriptions!", Toast.LENGTH_SHORT).show();
//        }
//    }
}

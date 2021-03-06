package com.example.paetsch_subbook;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by aapae on 1/30/2018.
 * Custom Adapter for Subscriptions adapted from
 * https://github.com/codepath/android_guides/wiki/Using-an-ArrayAdapter-with-ListView
 */

public class CustomAdapter extends ArrayAdapter<Subscription> {
    private ArrayList<Subscription> subscriptions;
    public CustomAdapter(Context context, ArrayList<Subscription> subscriptions){
        super(context, R.layout.customlist, subscriptions);
    }

    /**
     * Gets the data from a subscription and defines what happens when a list view item is clicked
     * @param pos
     * @param convertView
     * @param parent
     * @return
     */
    @Override
    public View getView(final int pos, View convertView, ViewGroup parent){
        LayoutInflater inf = LayoutInflater.from(getContext());
        final View data = inf.inflate(R.layout.customlist, parent, false);
        // gets the data to be displayed for a list item based on its position in the array
        String subscriptionName = getItem(pos).getSubscriptionName();
        String subscriptionDate = getItem(pos).getSubscriptionDate();
        float subscriptionCharge = getItem(pos).getSubscriptionCharge();

        TextView sub_name = (TextView) data.findViewById(R.id.List_Name);
        TextView sub_date = (TextView) data.findViewById(R.id.List_Date);
        TextView sub_charge = (TextView) data.findViewById(R.id.List_Charge);

        LinearLayout thisButton = (LinearLayout) data.findViewById(R.id.list_main);

        //setting
        sub_name.setText(subscriptionName);
        sub_date.setText(subscriptionDate);

        String sub_charge_string = String.valueOf(subscriptionCharge);
        sub_charge.setText(sub_charge_string);
        // on click listener for clicking a list item
        thisButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                editSubscription(pos);
            }
        });
        return data;
    }
    /**
     * Method to invoke the intent of clicking on a list item
     * @param pos
     * pos is a variable defining the items position in the list, this position is passed on through intent
     */
    private void editSubscription(int pos){
        Intent intent = new Intent(getContext(), editDescription.class);
        intent.putExtra(MainActivity.POINTER, String.valueOf(pos));
        ((Activity)getContext()).startActivityForResult(intent, 0);

    }

}

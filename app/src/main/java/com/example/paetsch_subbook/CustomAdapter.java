package com.example.paetsch_subbook;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by aapae on 1/30/2018.
 */

public class CustomAdapter extends ArrayAdapter<Subscription> {
    public CustomAdapter(Context context, ArrayList<Subscription> subscriptions){
        super(context, R.layout.customlist, subscriptions);
    }
    @Override
    public View getView(final int pos, View convertView, ViewGroup parent){
        LayoutInflater inf = LayoutInflater.from(getContext());
        final View data = inf.inflate(R.layout.customlist, parent, false);

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

        thisButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                editSubscription(pos);
            }
        });
        return data;
    }

    public void editSubscription(int pos){
        Intent intent = new Intent(getContext(), editDescription.class);
        //Todo: figure out the text below and why it is there
        intent.putExtra("Subscription_Position", pos);
        ((Activity)getContext()).startActivityForResult(intent, 0);

    }

}

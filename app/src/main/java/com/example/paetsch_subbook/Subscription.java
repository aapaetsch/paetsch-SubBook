package com.example.paetsch_subbook;

import java.io.Serializable;


/**
 * Created by aapae on 1/30/2018.
 */

public class Subscription implements Serializable {

    private static final long serialVersionID = -42L;
    protected String subscriptionName;
    protected String subscriptionDate;
    protected float subscriptionCharge;
    protected String subscriptionComment;

    @Override
    public String toString() {
        return "Subscription{" +
                "subscriptionName='" + subscriptionName + '\'' +
                ", subscriptionDate='" + subscriptionDate + '\'' +
                ", subscriptionCharge=" + subscriptionCharge + '}';
    }

    public Subscription(String subscriptionName, String subscriptionDate, float subscriptionCharge, String subscriptionComment){
        this.subscriptionName = subscriptionName;
        this.subscriptionDate = subscriptionDate;
        this.subscriptionCharge = subscriptionCharge;
        this.subscriptionComment = subscriptionComment;
    }
    public String getSubscriptionName(){
        return this.subscriptionName;
    }
    public String getSubscriptionDate(){
        return this.subscriptionDate;
    }
    public float getSubscriptionCharge(){
        return this.subscriptionCharge;
    }
    public String getSubscriptionComment(){
        return this.subscriptionComment;
    }
    public void editSubscription(String subscriptionName, String subscriptionDate, float subscriptionCharge, String subscriptionComment){
        this.subscriptionName = subscriptionName;
        this.subscriptionDate = subscriptionDate;
        this.subscriptionCharge = subscriptionCharge;
        this.subscriptionComment = subscriptionComment;
    }

}

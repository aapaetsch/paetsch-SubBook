package com.example.paetsch_subbook;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by aapae on 1/29/2018.
 */

public class subscription implements Serializable{
    private String subscriptionName;
    private String subscriptionDate;
    private String subscriptionCharge;
    private String subscriptionComment;
    private static final long serialVersionUID = -42L;

    public subscription(String subscriptionName, String subscriptionDate, String subscriptionCharge, String subscriptionComment ){
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
    public String getSubscriptionCharge(){
        return this.subscriptionCharge;
    }
    public String getSubscriptionComment(){
        return this.subscriptionComment;
    }




}

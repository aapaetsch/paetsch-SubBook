package com.example.paetsch_subbook;

/**
 * @author Created by Aidan Paetsch
 * @date 1/30/2018.
 * Class for a singular subscription, takes in a name, date, cost(as a float value) and optional comment
 */

public class Subscription{

    private String subscriptionName;
    private String subscriptionDate;
    private float subscriptionCharge;
    private String subscriptionComment;

    /**
     * method that returns the string of each element of a subscription
     * @return returns string values of subscription object
     */
    @Override
    public String toString() {
        return "Subscription{" +
                "subscriptionName='" + subscriptionName + '\'' +
                ", subscriptionDate='" + subscriptionDate + '\'' +
                ", subscriptionCharge=" + subscriptionCharge +
                ", subscriptionComment='" + subscriptionComment + '\'' +
                '}';
    }
    /**
     * Takes in all elements of a subscription, used to create a new Subscription object
     * @param subscriptionName String name of the subscription object
     * @param subscriptionDate String Date of the subscription object
     * @param subscriptionCharge Float cost of the subscription object
     * @param subscriptionComment String Comment for the subscription object
     */
    public Subscription(String subscriptionName, String subscriptionDate, float subscriptionCharge, String subscriptionComment){
        this.subscriptionName = subscriptionName;
        this.subscriptionDate = subscriptionDate;
        this.subscriptionCharge = subscriptionCharge;
        this.subscriptionComment = subscriptionComment;
    }
    /**
     * Set of getters for Subscription used to get each element of
     * a particular subscription
     */
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
    /**
     * Takes in all elements of a subscription entry and updates it in a singular
     * method as opposed to individual methods for each
     * @param subscriptionName
     * @param subscriptionDate
     * @param subscriptionCharge
     * @param subscriptionComment
     */
    public void setCurrentSub(String subscriptionName, String subscriptionDate, float subscriptionCharge, String subscriptionComment){
        this.subscriptionName = subscriptionName;
        this.subscriptionComment = subscriptionComment;
        this.subscriptionCharge = subscriptionCharge;
        this.subscriptionDate = subscriptionDate;
    }
}

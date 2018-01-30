package com.example.paetsch_subbook;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


/**
 * Created by aapae on 1/29/2018.
 */

public class subscriptionList {
    private static final long serialVersionUID = -43L;
    protected ArrayList<subscription> subscriptionlist = null;
    protected transient ArrayList<Listener> listeners = null;

    public subscriptionList(){
        subscriptionlist = new ArrayList<subscription>();
        listeners = new ArrayList<Listener>();

    }
    private ArrayList<Listener> getListeners(){
        if (listeners == null){
            listeners = new ArrayList<Listener>();
        }
        return listeners;
    }
    public Collection<subscription> getSubscription(){
        return subscriptionlist;
    }

    public void notifyListeners(){
        for (Listener listener : getListeners()){
            listener.update();
        }
    }
    public int size(){
        return subscriptionlist.size();
    }

    public void addListener(Listener 1){
        getListeners().add(1);
    }
    public void removeListener(Listener 1){
        getListeners().remove(1);
    }



}

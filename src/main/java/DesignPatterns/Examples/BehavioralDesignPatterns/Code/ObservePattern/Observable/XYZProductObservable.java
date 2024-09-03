package DesignPatterns.Examples.BehavioralDesignPatterns.Code.ObservePattern.Observable;

import DesignPatterns.Examples.BehavioralDesignPatterns.Code.ObservePattern.Observer.NotificationAlertObserver;

import java.util.ArrayList;
import java.util.List;

public class XYZProductObservable implements StockObservable{

    public List<NotificationAlertObserver> observerList=new ArrayList<>();
    public int stockCount=0;

    @Override
    public void add(NotificationAlertObserver observer) {

    }

    @Override
    public void remove(NotificationAlertObserver observer) {

    }

    @Override
    public void notifySubscribers() {

    }

    @Override
    public void setStockCount(int newStockAdded) {

    }

    @Override
    public int getStockCount() {
        return 0;
    }
}

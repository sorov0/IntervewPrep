package DesignPatterns.Examples.BehavioralDesignPatterns.Code.ObservePattern.Observable;

import DesignPatterns.Examples.BehavioralDesignPatterns.Code.ObservePattern.Observer.NotificationAlertObserver;

public interface StockObservable {

    public void add(NotificationAlertObserver observer);
    public void remove(NotificationAlertObserver observer);
    public  void notifySubscribers();
    public void setStockCount(int newStockAdded);
    public int getStockCount();
}

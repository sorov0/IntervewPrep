package DesignPatterns.Examples.BehavioralDesignPatterns.Code.ObservePattern.Observable;

import DesignPatterns.Examples.BehavioralDesignPatterns.Code.ObservePattern.Observer.EmailAlertObserverImpl;
import DesignPatterns.Examples.BehavioralDesignPatterns.Code.ObservePattern.Observer.MobileAlertObserverImpl;
import DesignPatterns.Examples.BehavioralDesignPatterns.Code.ObservePattern.Observer.NotificationAlertObserver;

public class Store {

    public static void main(String[] args) {

        StockObservable iphoneStockObservable=new IphoneStockObservable();

        NotificationAlertObserver observer1=new EmailAlertObserverImpl("abc@gmail.com", iphoneStockObservable);
        NotificationAlertObserver observer2=new EmailAlertObserverImpl("xyz@gmail.com", iphoneStockObservable);
        NotificationAlertObserver observer3=new MobileAlertObserverImpl("abc_username", iphoneStockObservable);

        iphoneStockObservable.add(observer1);
        iphoneStockObservable.add(observer2);
        iphoneStockObservable.add(observer3);

        iphoneStockObservable.setStockCount(10);
    }
}

package DesignPatterns.Examples.SolidPrinciples.Code.SRP;


// This class with three methods in it violating the SRP, It has three reason to change, this class is not following the SRP
public class Invoice {

    private Marker marker;
    private int qty;

    public Invoice(Marker marker, int qty) {
        this.marker = marker;
        this.qty = qty;
    }

    public int calulateTotal(){
        int price = (marker.price * this.qty);
        return price;
    }

    public void printinvoice(){
        //print the invoice
    }

    public void saveToDb(){
        //save data to DB

    }

}

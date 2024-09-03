package DesignPatterns.Examples.SolidPrinciples.Code.SRP;

public class InvoiceCalculation extends InvoiceNew {

    public InvoiceCalculation(Marker marker, int qty) {
        super(marker, qty);
    }

    public int calulateTotal(Marker marker, int qty){
        int price = (marker.price * qty);
        return price;
    }
}

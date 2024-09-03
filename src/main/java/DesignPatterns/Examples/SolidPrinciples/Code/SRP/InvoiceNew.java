package DesignPatterns.Examples.SolidPrinciples.Code.SRP;

public class InvoiceNew {

    private Marker marker;
    private int qty;

    public InvoiceNew(Marker marker, int qty) {
        this.marker = marker;
        this.qty = qty;
    }
}

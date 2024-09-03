package DesignPatterns.Examples.SolidPrinciples.Code.OFECFMP;

import DesignPatterns.Examples.SolidPrinciples.Code.SRP.Invoice;

// This class is not following the OECMP, Coz this class is getting modified adding a new function saveToFile
// as new requirements came in.
public class InvoiceDao {

    private Invoice invoice;

    public InvoiceDao(Invoice invoice) {
        this.invoice = invoice;
    }

    public void saveToDb(){
        //save data to DB
    }

    public void saveToFile(){
        //save data to File
    }

}

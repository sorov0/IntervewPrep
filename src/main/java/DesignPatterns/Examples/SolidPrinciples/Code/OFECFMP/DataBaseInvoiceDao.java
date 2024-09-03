package DesignPatterns.Examples.SolidPrinciples.Code.OFECFMP;

import DesignPatterns.Examples.SolidPrinciples.Code.SRP.Invoice;

public class DataBaseInvoiceDao implements IInvoiceDao{
    @Override
    public void save(Invoice invoice) {
        //save to Database
    }
}

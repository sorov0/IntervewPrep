package DesignPatterns.Examples.SolidPrinciples.Code.OFECFMP;

import DesignPatterns.Examples.SolidPrinciples.Code.SRP.Invoice;

public class FileInvoiceDao implements IInvoiceDao {

    @Override
    public void save(Invoice invoice) {
        //save to File
    }
}

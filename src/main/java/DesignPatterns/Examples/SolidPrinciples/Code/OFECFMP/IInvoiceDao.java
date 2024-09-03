package DesignPatterns.Examples.SolidPrinciples.Code.OFECFMP;

import DesignPatterns.Examples.SolidPrinciples.Code.SRP.Invoice;

public interface IInvoiceDao {
    public void save(Invoice invoice);
}

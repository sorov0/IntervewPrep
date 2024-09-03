package DesignPatterns.Examples.SolidPrinciples.Code.SRP;

public class InvoiceDao {

    private InvoiceNew invoice;

    public InvoiceDao(InvoiceNew invoice) {
        this.invoice = invoice;
    }

    public void saveToDb(){
        //save data to DB

    }
}

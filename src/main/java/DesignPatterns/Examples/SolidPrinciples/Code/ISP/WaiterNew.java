package DesignPatterns.Examples.SolidPrinciples.Code.ISP;

// This class is now following the ISP as Interface has now been segregated and
// This class is implementing only those methods that it requires.

public class WaiterNew implements WaiterInterface{

    @Override
    public void serveCustomers() {

    }

    @Override
    public void takeOrders() {

    }
}

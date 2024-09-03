package DesignPatterns.Examples.SolidPrinciples.Code.ISP;

// This class is implementing all the functions of the RestaurantEmployee interface,
// But it does not require to implement washDishes and cookFood method as Waiter would only perform the task of
// serving the customers, So this design does not follow ISP.
// In order to achieve ISP, we need to segregate the interface.

public class Waiter implements RestaurantEmployee{

    @Override
    public void wasDishes() {
        // not my job
    }

    @Override
    public void serveCustomer() {

    }

    @Override
    public void cookFood() {
        // not my job
    }
}

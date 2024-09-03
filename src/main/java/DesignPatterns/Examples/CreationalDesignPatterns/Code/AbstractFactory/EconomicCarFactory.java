package DesignPatterns.Examples.CreationalDesignPatterns.Code.AbstractFactory;

public class EconomicCarFactory implements AbstractFactory {

    @Override
    public Car getInstance(int price) {
        if(price < 500000){
            return new EconomicCar1();
        } else if (price > 500000 && price < 1000000) {
            return new EconomicCar2();
        }else {
            return null;
        }
    }
}

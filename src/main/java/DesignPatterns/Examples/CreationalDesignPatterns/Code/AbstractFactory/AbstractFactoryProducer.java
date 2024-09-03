package DesignPatterns.Examples.CreationalDesignPatterns.Code.AbstractFactory;

public class AbstractFactoryProducer {

    public AbstractFactory getFactoryInstance(String value){
        if(value.equals("Economic")){
            return new EconomicCarFactory();
        } else if (value.equals("LuxuryCar") || value.equals("Premium")) {
            return new LuxuryCarFactory();
        }else{
            return null;
        }
    }

    public static void main(String[] args) {
        AbstractFactoryProducer abstractFactoryProducer = new AbstractFactoryProducer();
        AbstractFactory abstractFactory = abstractFactoryProducer.getFactoryInstance("Premium");

        Car obj = abstractFactory.getInstance(70000000);
        System.out.println(obj.getTopSpeed());
    }
}

package DesignPatterns.Examples.BehavioralDesignPatterns.Code.StrategyPattern.WithStrategyPattern.Strategy;

public class NormalDriveStrategy implements DriveStrategy{
    @Override
    public void drive() {
        //some code
        System.out.println("normal drive capability");
    }
}

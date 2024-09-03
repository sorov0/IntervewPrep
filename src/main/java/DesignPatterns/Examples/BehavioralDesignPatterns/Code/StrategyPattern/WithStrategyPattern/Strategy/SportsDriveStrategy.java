package DesignPatterns.Examples.BehavioralDesignPatterns.Code.StrategyPattern.WithStrategyPattern.Strategy;

public class SportsDriveStrategy implements DriveStrategy {
    @Override
    public void drive() {
        //Different drive logic
        System.out.println("Sports drive capability");
    }
}

package DesignPatterns.Examples.BehavioralDesignPatterns.Code.StrategyPattern.WithStrategyPattern.Vehicle;

import DesignPatterns.Examples.BehavioralDesignPatterns.Code.StrategyPattern.WithStrategyPattern.Strategy.DriveStrategy;

public class Vehicle {

    /*
        DriveStrategy driveStrategyNormal = new NormalDriveStrategy();
        DriveStrategy driveStrategySports = new SportsDriveStrategy();

    //We are not doing this as it lead to tight Coupling and tight bound

    */

    DriveStrategy driveStrategy;

    //This is called Constructor Injection
    public Vehicle(DriveStrategy driveStrategy) {
        this.driveStrategy = driveStrategy;
    }

    void drive(){
        driveStrategy.drive();
    }
}

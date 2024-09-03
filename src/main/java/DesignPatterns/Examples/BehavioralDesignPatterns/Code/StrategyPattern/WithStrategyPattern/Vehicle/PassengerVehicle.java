package DesignPatterns.Examples.BehavioralDesignPatterns.Code.StrategyPattern.WithStrategyPattern.Vehicle;

import DesignPatterns.Examples.BehavioralDesignPatterns.Code.StrategyPattern.WithStrategyPattern.Strategy.DriveStrategy;
import DesignPatterns.Examples.BehavioralDesignPatterns.Code.StrategyPattern.WithStrategyPattern.Strategy.NormalDriveStrategy;

public class PassengerVehicle extends Vehicle{

    public PassengerVehicle() {
        super(new NormalDriveStrategy());
    }
}

package DesignPatterns.Examples.BehavioralDesignPatterns.Code.StrategyPattern.WithStrategyPattern.Vehicle;

import DesignPatterns.Examples.BehavioralDesignPatterns.Code.StrategyPattern.WithStrategyPattern.Strategy.DriveStrategy;
import DesignPatterns.Examples.BehavioralDesignPatterns.Code.StrategyPattern.WithStrategyPattern.Strategy.SportsDriveStrategy;

public class SportsVehicle extends Vehicle{

    public SportsVehicle() {
        super(new SportsDriveStrategy());
    }
}

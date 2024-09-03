package DesignPatterns.Examples.BehavioralDesignPatterns.Code.StrategyPattern.WithoutStrategyPattern;

public class OffRoadVehicle extends Vehicle{

    void drive(){
        //Different drive logic
        System.out.println("Sports drive capability");
    }
    //OffRoad and Sports Vehicle need same drive logic
    //So it leads to code duplicacy in OffRoadVehicle and SportsVehicle Class, and this logic is not
    //there in Parents class
}

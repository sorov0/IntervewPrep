package DesignPatterns.Examples.StructualDesignPatterns.Code.AdapterPattern.Adaptee;

public class WeightMachineForBabies implements WeightMachine{

    @Override
    public double getWeightInPound() {
        return 28;
    }
}

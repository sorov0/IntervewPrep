package DesignPatterns.Examples.StructualDesignPatterns.Code.AdapterPattern.Client;

import DesignPatterns.Examples.StructualDesignPatterns.Code.AdapterPattern.Adaptee.WeightMachineForBabies;
import DesignPatterns.Examples.StructualDesignPatterns.Code.AdapterPattern.Adapter.WeightMachineAdapter;
import DesignPatterns.Examples.StructualDesignPatterns.Code.AdapterPattern.Adapter.WeightMachineAdapterImpl;

public class Main {

    public static void main(String args[]){

        WeightMachineAdapter weightMachineAdapter = new WeightMachineAdapterImpl(new WeightMachineForBabies());
        System.out.println(weightMachineAdapter.getWeightInKg());
    }
}

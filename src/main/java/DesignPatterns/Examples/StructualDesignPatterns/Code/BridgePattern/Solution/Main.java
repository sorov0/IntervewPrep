package DesignPatterns.Examples.StructualDesignPatterns.Code.BridgePattern.Solution;

public class Main {

    // In here Both can scale independently
    public static void main(String[] args) {

        LivingThings fishObj = new Fish(new WaterBreath());

        fishObj.breathProcess();
    }


}

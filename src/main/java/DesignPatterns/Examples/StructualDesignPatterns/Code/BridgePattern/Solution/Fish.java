package DesignPatterns.Examples.StructualDesignPatterns.Code.BridgePattern.Solution;


public class Fish extends LivingThings{

    public Fish(BreathImplementor breathImplementor) {
        super(breathImplementor);
    }

    @Override
    void breathProcess() {
        System.out.println("Fish is breathing");
    }
}

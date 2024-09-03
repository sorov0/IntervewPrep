package DesignPatterns.Examples.StructualDesignPatterns.Code.BridgePattern.Solution;

public class Dog extends LivingThings {

    public Dog(BreathImplementor breathImplementor) {
        super(breathImplementor);
    }

    @Override
    void breathProcess() {

    }

}

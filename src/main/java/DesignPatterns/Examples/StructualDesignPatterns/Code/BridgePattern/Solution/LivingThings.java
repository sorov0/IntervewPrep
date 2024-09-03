package DesignPatterns.Examples.StructualDesignPatterns.Code.BridgePattern.Solution;

public abstract class LivingThings {

    BreathImplementor breathImplementor;

    public LivingThings(BreathImplementor breathImplementor) {
        this.breathImplementor = breathImplementor;
    }

    abstract void breathProcess();

}

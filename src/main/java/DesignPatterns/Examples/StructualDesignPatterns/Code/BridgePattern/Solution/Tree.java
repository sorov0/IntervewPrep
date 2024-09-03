package DesignPatterns.Examples.StructualDesignPatterns.Code.BridgePattern.Solution;

public class Tree extends LivingThings {


    public Tree(BreathImplementor breathImplementor) {
        super(breathImplementor);
    }

    @Override
    void breathProcess() {

    }
}

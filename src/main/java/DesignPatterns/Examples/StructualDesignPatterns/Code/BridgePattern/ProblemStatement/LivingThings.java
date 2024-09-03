package DesignPatterns.Examples.StructualDesignPatterns.Code.BridgePattern.ProblemStatement;

public abstract class LivingThings {
    protected abstract void breathProcess();
}

// The question arises, How you would add a new BreathingProcess without adding any class of LivingThings ?
// In order to do that, we should have another class extending the LivingThings
// such as Bird class which extends Living Things, But this would be tughtly Coupled

// Solution:


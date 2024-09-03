package DesignPatterns.Examples.SolidPrinciples.Code.LSP;

public class ByCycle implements Vehicle {

    //This method not requires so this class is not following the LSP
    @Override
    public void turnOnEngine() {

        throw new AssertionError("There is no engine");

    }

    @Override
    public void accelerate() {

    }
}

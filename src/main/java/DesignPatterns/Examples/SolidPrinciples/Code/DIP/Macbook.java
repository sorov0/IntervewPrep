package DesignPatterns.Examples.SolidPrinciples.Code.DIP;

// This class is not following the DIP as It is using the Concrete class to implement the MacBook Object
// Lets say, Later on we want to initialize the Macbook object with bluetooth keyboard and mouse then
// in that case we have to change the class.
public class Macbook {

    private final WiredKeyboard keyboard;
    private final WiredMouse wiredMouse;

    public Macbook() {
        keyboard = new WiredKeyboard();
        wiredMouse = new WiredMouse();
    }
}

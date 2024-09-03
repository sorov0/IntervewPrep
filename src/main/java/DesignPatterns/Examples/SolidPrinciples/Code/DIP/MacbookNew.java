package DesignPatterns.Examples.SolidPrinciples.Code.DIP;

// This class is following the DIP, as It has used the two objects keyboard and mouse as interface and
// It can be used to create Macbook Objects with both WiredKeyboard and BluetoothKeyboard
public class MacbookNew {

    private final Keyboard keyboard;
    private final Mouse mouse;

    public MacbookNew(Keyboard keyboard, Mouse mouse) {
        this.keyboard = keyboard;
        this.mouse = mouse;
    }
}

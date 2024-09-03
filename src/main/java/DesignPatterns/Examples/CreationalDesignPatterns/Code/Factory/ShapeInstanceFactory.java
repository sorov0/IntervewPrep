package DesignPatterns.Examples.CreationalDesignPatterns.Code.Factory;

// It has one Factory Class with a method which is taking the responsibility of creating the object based on client input.
public class ShapeInstanceFactory {


    public Shape getShapeInstance(String value){
        if(value.equals("Circle")){
            return new Circle();
        } else if (value.equals("Square")) {
            return new Square();
        } else if (value.equals("Rectangle")) {
            return new Rectangle();
        }else {
            return null;
        }
    }

    public static void main(String[] args) {
        ShapeInstanceFactory shapeInstanceFactory = new ShapeInstanceFactory();
        Shape circleObj = shapeInstanceFactory.getShapeInstance("Circle");
        circleObj.computeArea();
    }
}

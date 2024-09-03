package DesignPatterns.Examples.CreationalDesignPatterns.Code.ProtoType;

public class Student {

    String name;
    int age;
    private int rollnumber;

    public Student() {

    }

    public Student(String name, int age, int rollnumber) {
        this.name = name;
        this.age = age;
        this.rollnumber = rollnumber;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public int getRollnumber() {
        return rollnumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setRollnumber(int rollnumber) {
        this.rollnumber = rollnumber;
    }

    public static void main(String[] args) {

        // Problems while cloning the objects with normal existing ways:
        // 1. Private attributes of an object are not accessible in another class while cloning.
        //    We can access it using getter method, but suppose even getter method is made private,
        //    We cannot access that attribute, so cloning an object in another class would not be possible.

        // 2. Suppose an object has too many attributes, and in different scenarios, Suppose we want to clone
        //    that object with less number of attributes or more number of attributes, In such cases, Each different class,
        //    who is cloning different number of attributes, must be aware of all its attributes. This again
        //    is not a good design and is prone to error.
        //    Meaning, the Client(in which cloning code or logic is there), has to know about all its attributes.

        // Solution:
        // We should have a prototype interface with clone method.
        // Each different class whose objects need to be cloned, must implement this prototype interface
        // and override the clone method. Meaning the cloning code or logic should be the part of the class
        // only, whose object we want to clone.
        // This way, issue with accessing the private attribute gets resolved and since the cloning logic is
        // inside the Class only, so client need not to be aware of the different attributes of that class.
        // Client would simply call the clone method and make the copy of the object while creating new object.


        Student obj = new Student("Saurav" , 25 , 2);

        Student cloneObj = new Student();
        cloneObj.name = obj.name;
        cloneObj.age = obj.age;

        // Private member is not accessible in another class.
        // Note: Rollnumber being a private member is directly accessible here coz it is being accessed
        // in the same class only
        cloneObj.rollnumber = obj.rollnumber;




    }
}

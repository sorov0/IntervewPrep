package DesignPatterns.Examples.CreationalDesignPatterns.Code.ProtoType;

public class StudentNew implements IPrototype{

    String name;
    int age;
    private int rollnumber;

    public StudentNew() {

    }

    public StudentNew(String name, int age, int rollnumber) {
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

    @Override
    public Object clone()
    {
        return new StudentNew("saurav" , 25 , 12);
    }

    public static void main(String[] args) {

        StudentNew obj = new StudentNew("Saurav" , 25 , 2);

        StudentNew cloneObj = (StudentNew) obj.clone();
    }
}

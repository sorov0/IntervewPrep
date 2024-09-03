package DesignPatterns.Examples.CreationalDesignPatterns.Code.ProtoType;

public class Employee implements IPrototype {

    String name;
    int age;
    int salary;

    public Employee() {

    }

    public Employee(String name, int age, int salary) {
        this.name = name;
        this.age = age;
        this.salary = salary;
    }

    @Override
    public Object clone() {
        return new Employee("saurav" , 25 , 12);
    }

    public static void main(String[] args) {

        Employee obj = new Employee("Saurav" , 25 , 2);

        Employee cloneObj = (Employee) obj.clone();
    }
}

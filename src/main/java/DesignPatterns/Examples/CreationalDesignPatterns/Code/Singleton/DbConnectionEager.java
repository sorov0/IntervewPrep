package DesignPatterns.Examples.CreationalDesignPatterns.Code.Singleton;


// In this method, We restrict the object creation by making the constructor of the class private
// and then we expose a method that another classes can call to get the instance of that class
// and then We create a new object of that class and assign it to a static variable(related to the class and not object) of that class.
public class DbConnectionEager {

    private static DbConnectionEager dbConnectionEager = new DbConnectionEager();

    private DbConnectionEager(){

    }

    public static DbConnectionEager getInstance(){
        return dbConnectionEager;
    }

    public static void main(String[] args) {
        DbConnectionEager connObject = DbConnectionEager.getInstance();
    }


}

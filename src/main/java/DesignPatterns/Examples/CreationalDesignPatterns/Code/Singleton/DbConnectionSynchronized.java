package DesignPatterns.Examples.CreationalDesignPatterns.Code.Singleton;

public class DbConnectionSynchronized {

    private static DbConnectionSynchronized dbConnectionSynchronized = null;

    private DbConnectionSynchronized(){

    }

    synchronized public static DbConnectionSynchronized getInstance(){
        if(dbConnectionSynchronized == null){
            dbConnectionSynchronized = new DbConnectionSynchronized();
        }
        return dbConnectionSynchronized;
    }

    public static void main(String[] args) {
        DbConnectionSynchronized connObject = DbConnectionSynchronized.getInstance();
    }
}

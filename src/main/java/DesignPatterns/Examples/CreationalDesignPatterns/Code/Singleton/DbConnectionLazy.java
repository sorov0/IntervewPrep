package DesignPatterns.Examples.CreationalDesignPatterns.Code.Singleton;

public class DbConnectionLazy {

    private static DbConnectionLazy dbConnectionLazy = null;

    private DbConnectionLazy(){

    }

    public static DbConnectionLazy getInstance(){
        if(dbConnectionLazy == null){
            dbConnectionLazy = new DbConnectionLazy();
        }
        return dbConnectionLazy;
    }

    public static void main(String[] args) {
        DbConnectionLazy connObject = DbConnectionLazy.getInstance();
    }


}

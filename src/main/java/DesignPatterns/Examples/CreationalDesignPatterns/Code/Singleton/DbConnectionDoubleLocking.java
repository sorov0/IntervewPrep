package DesignPatterns.Examples.CreationalDesignPatterns.Code.Singleton;

public class DbConnectionDoubleLocking {

    private static DbConnectionDoubleLocking dbConnectionDoubleLocking = null;

    private DbConnectionDoubleLocking(){

    }

    synchronized public static DbConnectionDoubleLocking getInstance(){
        if(dbConnectionDoubleLocking == null){
            synchronized (DbConnectionDoubleLocking.class){
                if(dbConnectionDoubleLocking == null){
                    dbConnectionDoubleLocking = new DbConnectionDoubleLocking();
                }
            }
        }
        return dbConnectionDoubleLocking;
    }

    public static void main(String[] args) {
        DbConnectionDoubleLocking connObject = DbConnectionDoubleLocking.getInstance();
    }
}

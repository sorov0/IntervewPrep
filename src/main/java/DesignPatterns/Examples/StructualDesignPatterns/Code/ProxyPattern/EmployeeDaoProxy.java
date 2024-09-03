package DesignPatterns.Examples.StructualDesignPatterns.Code.ProxyPattern;

public class EmployeeDaoProxy implements EmployeeDao{

    EmployeeDao employeeDao;

    public EmployeeDaoProxy() {
        this.employeeDao = new EmployeeDaoImpl();
    }

    @Override
    public void create() {
        //Some business logic to control the access of the original Object
        if(true){
            employeeDao.create();
        }else{
            throw new RuntimeException("Exception");
        }
    }

    public static void main(String[] args) {
        EmployeeDao employeeDaoObj = new EmployeeDaoProxy();
        employeeDaoObj.create();
    }
}

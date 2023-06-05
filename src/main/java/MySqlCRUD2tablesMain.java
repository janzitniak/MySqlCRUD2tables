import sk.tmconsulting.crud2tables.model.Department;
import sk.tmconsulting.crud2tables.service.DepartmentService;
import sk.tmconsulting.crud2tables.model.Employee;
import sk.tmconsulting.crud2tables.service.EmployeeService;

import java.sql.SQLException;

public class MySqlCRUD2tablesMain {
    public static void main(String[] args) {

        // Vytvorenie DepartmentService, aby sme napojili na databazu a pracovali s CRUD operaciami
        DepartmentService departmentService = new DepartmentService();
        try {
            departmentService.setConnection(); // Pripojenie na databazu
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        // Ziskanie Department
        Department department = null;
        try {
            department = departmentService.readDepartmentById(1);
            //department = departmentService.readDepartmentByName("IT Oddelenie");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        // Vytvorenie objektu Employee
        Employee employee1 = new Employee();
        employee1.setName("John Doe");
        employee1.setAge(30);
        employee1.setDepartment(department);

        // Vytvorenie EmployeeService, aby sme napojili na databazu a pracovali s CRUD operaciami
        EmployeeService employeeService = new EmployeeService();
        try {
            employeeService.setConnection(); // Pripojenie na databazu
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        // Vytvorenie Employee
        try {
            employeeService.createEmployee(employee1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        // Nacitanie Employee
        Employee employeeFromDatabase;
        try {
            employeeFromDatabase = employeeService.readEmployeeById(1);
            //employeeService.getEmployeesByName("John");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if (employeeFromDatabase!=null) {
            System.out.println("\nNájdený ZAMESTNANEC v databáze");
            System.out.println(employeeFromDatabase);
        }
        System.out.println("Nebol nájdený žiadny zamestnanec!");

        // Nacitanie Employees
        /*
        ArrayList<Employee> employeesFromDatabase;
        try {
            employeesFromDatabase = employeeService.getEmployeesByName("ll");
            //employeeService.getEmployeesByName("John");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if (!employeesFromDatabase.isEmpty()) {
            System.out.println("\nNájdení ZAMESTNANCI v databáze");
            for (Employee employee : employeesFromDatabase) {
                System.out.println(employee);
            }
        } else {
            System.out.println("Žiadny ZAMESTNANEC nebol nájdený!");
        }*/



        // Aktualizacia Employee
/*        employee.setAge(35);
        try {
            employeeService.updateEmployee(employee);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }*/


        // Odstranenie Employee
/*        try {
            employeeService.deleteEmployee(employee);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }*/

    }

}

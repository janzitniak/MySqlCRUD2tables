import sk.tmconsulting.crud2tables.model.Department;
import sk.tmconsulting.crud2tables.repository.DepartmentRepository;
import sk.tmconsulting.crud2tables.model.Employee;
import sk.tmconsulting.crud2tables.repository.EmployeeRepository;

import java.sql.SQLException;

public class MySqlCRUD2tablesMain {
    public static void main(String[] args) {

        // Vytvorenie DepartmentRepository, aby sme napojili na databazu a pracovali s CRUD operaciami
        DepartmentRepository departmentRepository = new DepartmentRepository();
        try {
            departmentRepository.setConnection(); // Pripojenie na databazu
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        // Ziskanie Department
        Department department = null;
        try {
            department = departmentRepository.readDepartmentById(1);
            //department = departmentRepository.readDepartmentByName("IT Oddelenie");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        // Vytvorenie objektu Employee
        Employee employee1 = new Employee();
        employee1.setName("John Doe");
        employee1.setAge(30);
        employee1.setDepartment(department);

        // Vytvorenie EmployeeService, aby sme napojili na databazu a pracovali s CRUD operaciami
        EmployeeRepository employeeRepository = new EmployeeRepository();
        try {
            employeeRepository.setConnection(); // Pripojenie na databazu
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        // Vytvorenie Employee
        try {
            employeeRepository.createEmployee(employee1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        // Nacitanie Employee
        Employee employeeFromDatabase;
        try {
            employeeFromDatabase = employeeRepository.readEmployeeById(1);
            //employeeRepository.getEmployeesByName("John");
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
            employeesFromDatabase = employeeRepository.getEmployeesByName("ll");
            //employeeRepository.getEmployeesByName("John");
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
            employeeRepository.updateEmployee(employee);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }*/


        // Odstranenie Employee
/*        try {
            employeeRepository.deleteEmployee(employee);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }*/

    }

}

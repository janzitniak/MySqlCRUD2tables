import sk.tmconsulting.crud2tabulky.model.Employee;
import sk.tmconsulting.crud2tabulky.model.EmployeeService;

import java.sql.SQLException;

public class MySqlCRUD2tablesMain {
    public static void main(String[] args) {

        // Vytvorenie objektu Employee
        Employee employee = new Employee();
        employee.setName("John Doe");
        employee.setAge(30);
        employee.setDepartmentID(1);


        // Vytvorenie EmployeeService, aby sme napojili na databazu a pracovali s CRUD operaciami
        EmployeeService employeeService = new EmployeeService();
        try {
            employeeService.setCon(); // Pripojenie na databazu
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        // Vytvorenie Employee
        try {
            employeeService.createEmployee(employee);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        // Nacitanie Employee
        try {
            employeeService.readEmployeeById(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        // Aktualizacia Employee
        employee.setAge(35);
        try {
            employeeService.updateEmployee(employee);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        // Odstranenie Employee
        try {
            employeeService.deleteEmployee(employee);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}

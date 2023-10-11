import sk.tmconsulting.crud2tables.model.Department;
import sk.tmconsulting.crud2tables.model.Employee;
import sk.tmconsulting.crud2tables.repository.DepartmentRepository;
import sk.tmconsulting.crud2tables.repository.EmployeeRepository;

import java.sql.SQLException;
import java.util.ArrayList;

public class MySqlCRUD2tablesMain {
    public static void main(String[] args) throws SQLException {
        EmployeeRepository er = new EmployeeRepository();
        // Vytvorime oddelenie a potom az zamestnanca
/*        Department department1 = new Department(3, "Ekonomické oddelenie");
        // Vytvorit zamestnanca a priradit ho k oddeleniu
        Employee employee1 = new Employee("Jožko Mrkvička", 29, department1); // Vytvorili sme "prazdneho" zamestnanca
        System.out.println("Zamestnanec employee1 obsahuje tieto údaje: " + employee1);


        DepartmentRepository dr = new DepartmentRepository();

        try {
            er.setConnection(); // Pred samotnou pracou s MySQL databazou sa musime na nu napojit!
            dr.setConnection();
            dr.createDepartment(department1);
            er.createEmployee(employee1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }*/


        try {
            //System.out.println(er.getAllEmployees()); // Ziskame ArrayList vsetkych zaznamov, teda employees
            ArrayList<Employee> allEmployees = er.getAllEmployees();
            for (Employee employeeObject : allEmployees) {
                System.out.println(employeeObject);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }



/*        if (employee1 != null) {
            // Ak nie je null mozeme s nim dalej pracovat
            System.out.println("Objekt employee1 NIE JE null");
        }*/

    }

}

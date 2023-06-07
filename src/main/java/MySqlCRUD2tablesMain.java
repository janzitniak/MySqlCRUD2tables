import sk.tmconsulting.crud2tables.model.Department;
import sk.tmconsulting.crud2tables.repository.DepartmentRepository;
import sk.tmconsulting.crud2tables.model.Employee;
import sk.tmconsulting.crud2tables.repository.EmployeeRepository;

import java.sql.SQLException;
import java.util.ArrayList;

public class MySqlCRUD2tablesMain {
    public static void main(String[] args) {
        // Vytvorit zamestnanca a priradit ho k oddeleniu
/*        Employee employee1 = new Employee(); // Vytvorili sme "prazdneho" zamestnanca
        employee1.setId(1);
        employee1.setName("Ján");
        employee1.setAge(27);

        Department department1 = new Department();
        department1.setId(1);
        department1.setName("IT oddelenie");

        employee1.setDepartment(department1); // Zamestnancovi employee1 sme nastavili department1

        System.out.println("Zamestnanec employee1 obsahuje tieto údaje: " + employee1);*/

        EmployeeRepository er = new EmployeeRepository();

        try {
            er.setConnection(); // Pred samotnou pracou s MySQL databazou sa musime na nu napojit!
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

/*        // A az potom pouzivat CRUD operacie
        // Vytvorenie zamestnanca / employee, cize C(reate)
        try {
            er.createEmployee(employee1);
        } catch (NullPointerException e1) {
            System.out.println("Chyba pri spojení s databázou MySQL!");
            System.out.println("Pretože sa nedokážem pripojiť na databázu MySQL, tak som nevedel uložiť zamestnanca!");
            throw new RuntimeException(e1); // Toto mi vypise detailne celu chybu!
        }
        catch (SQLException e) {
            System.out.println("Chyba pri vytváraní Zamestnanca!");
            throw new RuntimeException(e); // Toto mi vypise detailne celu chybu!
        }*/


        try {
/*            System.out.println(er.getAllEmployees()); // Ziskame ArrayList vsetkych zaznamov, teda employees*/
            ArrayList<Employee> allEmployees = er.getAllEmployees();
            for(Employee employeeObject:allEmployees) {
                System.out.println(employeeObject.toString());
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

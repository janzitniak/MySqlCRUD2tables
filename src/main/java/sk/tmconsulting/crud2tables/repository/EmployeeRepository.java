package sk.tmconsulting.crud2tables.repository;

import sk.tmconsulting.crud2tables.model.Department;
import sk.tmconsulting.crud2tables.model.Employee;

import java.sql.*;
import java.util.ArrayList;

public class EmployeeRepository {
    private Connection conn;

    public EmployeeRepository() throws SQLException {
        setConnection();
    }

    public void setConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/company_db";
        String username = "root";
        String password = "password";
        // Singleton pattern
        if (conn == null)
            conn = DriverManager.getConnection(url, username, password);
    }

    public void createEmployee(Employee employee) throws SQLException {
        String insertQuery = "INSERT INTO employee (name, age, department_id) VALUES (?, ?, ?)";
        PreparedStatement statement = conn.prepareStatement(insertQuery);
        statement.setString(1, employee.getName());
        statement.setInt(2, employee.getAge());
        statement.setInt(3, employee.getDepartment().getId());
        int rowsInserted = statement.executeUpdate();
        if (rowsInserted > 0) {
            System.out.println("Nový EMPLOYEE / ZAMESTNANEC bol úspešne vytvorený.");
        }
    }

    public Employee readEmployeeById(int id) throws SQLException {
        String selectQuery = "SELECT employee.id AS employee_id, employee.name AS employee_name, employee.age, " +
                "department.id AS deparment_id, department.name AS department_name " +
                "FROM employee" +
                "INNER JOIN department ON employee.department_id = department.id WHERE employee.id = ?";


        PreparedStatement statement = conn.prepareStatement(selectQuery);
        statement.setInt(1, id);

        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            int employeeID = resultSet.getInt("employee_id");
            String employeeName = resultSet.getString("employee_name");
            int employeeAge = resultSet.getInt("age");

            int departmentId = resultSet.getInt("deparment_id");
            String departmentName = resultSet.getString("department_name");

            Employee employee = new Employee();
            employee.setId(employeeID);
            employee.setName(employeeName);
            employee.setAge(employeeAge);

            Department department = new Department();
            department.setId(departmentId);
            department.setName(departmentName);

            employee.setDepartment(department);
            return employee;
        }
        return null;
    }

    public void updateEmployee(Employee employee) throws SQLException {
        String updateQuery = "UPDATE employee SET age = ? WHERE id = ?";

        PreparedStatement statement = conn.prepareStatement(updateQuery);

        statement.setInt(1, employee.getAge());
        statement.setInt(2, employee.getId());

        int rowsUpdated = statement.executeUpdate();
        if (rowsUpdated > 0) {
            System.out.println("Záznam bol úspešne aktualizovaný.");
        }
    }

    public void deleteEmployee(Employee employee) throws SQLException {
        String deleteQuery = "DELETE FROM employee WHERE id = ?";
        PreparedStatement statement = conn.prepareStatement(deleteQuery);
        statement.setInt(1, employee.getId());

        int rowsDeleted = statement.executeUpdate();
        if (rowsDeleted > 0) {
            System.out.println("Záznam bol úspešne odstránený.");
        }
    }

    public ArrayList<Employee> getEmployeesByName(String name) throws SQLException {
        String selectQuery = "SELECT employee.id AS employee_id, employee.name AS employee_name, employee.age, department.id AS deparment_id, department.name AS department_name " +
                "FROM employee " +
                "INNER JOIN department ON employee.department_id = department.id WHERE employee.name LIKE ?" ;

        PreparedStatement statement = conn.prepareStatement(selectQuery);
        statement.setString(1, "%" + name + "%"); // LIKE %John%

        ResultSet resultSet = statement.executeQuery();

        ArrayList<Employee> foundEmployees = new ArrayList<>();
        while (resultSet.next()) {
            int employeeID = resultSet.getInt("employee_id");
            String employeeName = resultSet.getString("employee_name");
            int employeeAge = resultSet.getInt("age");

            int departmentId = resultSet.getInt("deparment_id");
            String departmentName = resultSet.getString("department_name");

            Employee employee = new Employee();
            employee.setId(employeeID);
            employee.setName(employeeName);
            employee.setAge(employeeAge);

            Department department = new Department();
            department.setId(departmentId);
            department.setName(departmentName);

            employee.setDepartment(department);

            foundEmployees.add(employee);
        }
        return foundEmployees;
    }

    public ArrayList<Employee> getAllEmployees() throws SQLException {
        String selectQuery = "SELECT employee.id, employee.name, employee.age, employee.department_id, department.name FROM employee INNER JOIN department ON employee.department_id=department.id";
        PreparedStatement statement = conn.prepareStatement(selectQuery);
        ResultSet resultSet = statement.executeQuery(); // Vykoname dane query

        ArrayList<Employee> foundEmployees = new ArrayList<>();
        while (resultSet.next()) { // resultSet.next() prechadza kazdym zaznamom, ktore sme ziskali query uvedenom vyssie
            int employeeID = resultSet.getInt("employee.id");
            String employeeName = resultSet.getString("employee.name");
            int employeeAge = resultSet.getInt("employee.age");

            int departmentId = resultSet.getInt("employee.department_id");
            String departmentName = resultSet.getString("department.name");

            Employee employee = new Employee(); // Tymto vytvarame vzdy prazdny objekt employee a nasledne ho vyplname
            employee.setId(employeeID);
            employee.setName(employeeName);
            employee.setAge(employeeAge);

            Department department = new Department();
            department.setId(departmentId);
            department.setName(departmentName);

            employee.setDepartment(department);

            foundEmployees.add(employee);
        }
        return foundEmployees;

    }


}

package sk.tmconsulting.crud2tabulky.model;

import java.sql.*;

public class EmployeeService {
    private Connection conn;

    public void setCon() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/company_db";
        String username = "root";
        String password = "password";
        conn = DriverManager.getConnection(url, username, password);
    }

    public void createEmployee(Employee employee) throws SQLException {
        String insertQuery = "INSERT INTO employee (name, age, department_id) VALUES (?, ?, ?)";
        PreparedStatement statement = conn.prepareStatement(insertQuery);
        statement.setString(1, employee.getName());
        statement.setInt(2, employee.getAge());
        statement.setInt(3, employee.getDepartmentID());
        int rowsInserted = statement.executeUpdate();
        if (rowsInserted > 0) {
            System.out.println("Nový záznam bol úspešne vytvorený.");
        }
    }

    public Employee readEmployeeById(int id) throws SQLException {
        String selectQuery = "SELECT employee.id AS employee_id, employee.name AS employee_name, employee.age, department.id AS deparment_id, department.name AS department_name" +
                "FROM employee " +
                "INNER JOIN department ON employee.department_id = department.id WHERE employee.id = " + id;

        PreparedStatement statement = conn.prepareStatement(selectQuery);
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
            department.setDepartmentName(departmentName);

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

    public Employee readEmployeeByName(String name) throws SQLException {
        String selectQuery = "SELECT employee.id AS employee_id, employee.name AS employee_name, employee.age, department.id AS deparment_id, department.department_name AS department_name" +
                "FROM employee " +
                "INNER JOIN department ON employee.department_id = department.id WHERE employee.name LIKE = '%" + name +"%'" ;

        PreparedStatement statement = conn.prepareStatement(selectQuery);
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
            department.setDepartmentName(departmentName);

            employee.setDepartment(department);
            return employee;
        }
        return null;
    }

}

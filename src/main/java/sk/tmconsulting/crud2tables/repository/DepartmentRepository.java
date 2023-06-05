package sk.tmconsulting.crud2tables.repository;

import sk.tmconsulting.crud2tables.model.Department;

import java.sql.*;

public class DepartmentRepository {

    private Connection conn;

    public void setConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/company_db";
        String username = "root";
        String password = "password";
        conn = DriverManager.getConnection(url, username, password);
    }

    public void createDepartment(Department department) throws SQLException {
        String insertQuery = "INSERT INTO department (id, name) VALUES (?, ?)";
        PreparedStatement statement = conn.prepareStatement(insertQuery);
        statement.setInt(1, department.getId());
        statement.setString(2, department.getName());
        int rowsInserted = statement.executeUpdate();
        if (rowsInserted > 0) {
            System.out.println("Nové ODDELENIE /DEPARTMENT bol úspešne vytvorený.");
        }
    }

    public Department readDepartmentById(int id) throws SQLException {
        String selectQuery = "SELECT id, name FROM department WHERE id = ?";
        PreparedStatement statement = conn.prepareStatement(selectQuery);

        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();

        Department department = new Department();
        if (resultSet.next()) {
            int departmentId = resultSet.getInt("id");
            String departmentName = resultSet.getString("name");
            department.setId(departmentId);
            department.setName(departmentName);
        }
        return department;
    }

    public Department readDepartmentByName(String name) throws SQLException {
        String selectQuery = "SELECT id, name FROM department WHERE name = ?";

        PreparedStatement statement = conn.prepareStatement(selectQuery);
        statement.setString(1, name);
        ResultSet resultSet = statement.executeQuery();

        Department department = new Department();
        if (resultSet.next()) {
            int departmentId = resultSet.getInt("id");
            String departmentName = resultSet.getString("name");
            department.setId(departmentId);
            department.setName(departmentName);
        }
        return department;
    }


}

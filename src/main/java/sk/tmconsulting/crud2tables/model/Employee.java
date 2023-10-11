package sk.tmconsulting.crud2tables.model;

public class Employee {
    private int id;
    private String name;
    private int age;
    private int departmentId;
    private Department department;

    public Employee() {
    }

    public Employee(String name, int age, Department department) {
        this.name = name;
        this.age = age;
        this.departmentId = department.getId();
        this.department = department;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentID) {
        this.departmentId = departmentID;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", department=" + department +
                '}';
    }

}


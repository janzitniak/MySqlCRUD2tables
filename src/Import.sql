CREATE TABLE department (
  id INT PRIMARY KEY,
  name VARCHAR(50)
);

CREATE TABLE employee (
  id INT PRIMARY KEY,
  name VARCHAR(50),
  age INT,
  department_id INT,
  FOREIGN KEY (department_id) REFERENCES department(id)
);
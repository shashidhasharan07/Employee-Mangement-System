Employee Management System (EMS)
A Java Swing + JDBC project to manage employee records.

Features:

Add Employee (ONBOARD SUCCESSFul popup)

View All Employees (table view)

View Employee by ID

Update Employee (UPDATE SUCCESSFul popup)

Delete Employee (DEBOARD SUCCESSFul popup)

Back navigation between frames

Tech: Java, Swing, JDBC, MySQL, Eclipse.

Database Setup:

CREATE DATABASE feb;
USE feb;

CREATE TABLE employee(
 id INT AUTO_INCREMENT PRIMARY KEY,
 name VARCHAR(100),
 salary DOUBLE,
 department VARCHAR(100),
 position VARCHAR(100)
);


How to Run:

Add MySQL connector jar to Eclipse (Build Path).

Update DB credentials in DBUtil.java.

Run Home.java.

Project Structure:
DBUtil, Employee, Home, DisplayAll, SingleEmployee, OnboardEmployee, DeboardEmployee, UpdateEmployee.

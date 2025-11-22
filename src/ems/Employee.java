package ems;

public class Employee {
	
	    private int id;
	    private String name;
	    private double salary;
	    private String department;
	    private String position;

	    public Employee() {}

	    public Employee(int id, String name, double salary, String department, String position) {
	        this.id = id; this.name = name; this.salary = salary; this.department = department; this.position = position;
	    }

	    // getters/setters
	    public int getId() { return id; }
	    public void setId(int id) { this.id = id; }
	    public String getName() { return name; }
	    public void setName(String name) { this.name = name; }
	    public double getSalary() { return salary; }
	    public void setSalary(double salary) { this.salary = salary; }
	    public String getDepartment() { return department; }
	    public void setDepartment(String department) { this.department = department; }
	    public String getPosition() { return position; }
	    public void setPosition(String position) { this.position = position; }
	}



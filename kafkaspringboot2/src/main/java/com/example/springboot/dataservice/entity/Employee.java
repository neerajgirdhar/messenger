package com.example.springboot.dataservice.entity;

public class Employee {
    private String name;
    private String username;
    private int empId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    private String location;
    private String designation;
    public Employee(){}
    public Employee(String name, String username, int empId, String location, String designation) {
        this.name = name;
        this.username = username;
        this.empId = empId;
        this.location = location;
        this.designation = designation;
    }

    @Override
    public String toString() {

        final StringBuffer sb = new StringBuffer("Employee{");
        sb.append("name='").append(name).append('\'');
        sb.append(", username='").append(username).append('\'');
        sb.append("empId='").append(empId).append('\'');
        sb.append(", location='").append(location).append('\'');
        sb.append(", designation='").append(designation).append('\'');
        sb.append('}');
        return sb.toString();

    }
}

package com.aimprosoft.department.model;

import com.aimprosoft.department.entity.Department;
import com.aimprosoft.department.entity.Employee;

import java.text.SimpleDateFormat;
import java.util.Date;

public class EmployeeForm {


    private Integer id;
    private Department department;
    private String firstName;
    private String lastName;
    private String email;
    private String day;
    private String month;
    private String year;
    private Long inn;

    private Date birthday;

    public EmployeeForm() {}

    public EmployeeForm(Department department, String firstName, String lastName, String email, String day, String month, String year, Long inn) {
        this.department = department;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.day = day;
        this.month = month;
        this.year = year;
        this.inn = inn;
    }

    public EmployeeForm(Integer id, Department department, String firstName, String lastName, String email, String day, String month, String year, Long inn) {
        this.id = id;
        this.department = department;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.day = day;
        this.month = month;
        this.year = year;
        this.inn = inn;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Long getInn() {
        return inn;
    }

    public void setInn(Long inn) {
        this.inn = inn;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName.trim();
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email.trim();
    }

    public String getDay() {
        return day;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month.trim();
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year.trim();
    }

    public void setDay(String day) {
        this.day = day.trim();
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Employee saveEmployee() {
        return new Employee(id, department, firstName, lastName, email, new Date(month+"/"+day+"/"+year), inn);
    }

    public void loadEmployee(Employee employee) {
        id = employee.getId();
        department = employee.getDepartment();
        firstName = employee.getFirstName();
        lastName = employee.getLastName();
        email = employee.getEmail();
        Date employeeBirthday = employee.getBirthday();
        day = (new SimpleDateFormat("dd")).format(employeeBirthday);
        month = (new SimpleDateFormat("M")).format(employeeBirthday);
        year = (new SimpleDateFormat("yyyy")).format(employeeBirthday);
        inn = employee.getInn();
    }

}

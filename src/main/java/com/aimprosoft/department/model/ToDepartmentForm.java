package com.aimprosoft.department.model;

public class ToDepartmentForm {
    private Integer departmentId;
    private Integer[] employeeId;

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public Integer[] getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer[] employeeId) {
        this.employeeId = employeeId;
    }
}

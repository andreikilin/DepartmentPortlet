package com.aimprosoft.department.service;

import com.aimprosoft.department.entity.Department;
import com.aimprosoft.department.entity.Employee;

import java.util.List;

public interface EmployeeService extends Service<Employee>{
    Employee getByEmail(String email);
    Employee getByInn(Long inn);
    List<Employee> listByDepartment(Department department);
//    List<EmployeeJson> listJsonByDepartment(department department);
//    List<EmployeeJson> listJson();
}

package com.aimprosoft.department.dao;

import com.aimprosoft.department.entity.Department;
import com.aimprosoft.department.entity.Employee;

import java.util.List;

public interface EmployeeDao extends Dao<Employee> {
    Employee getByEmail(String email);
    Employee getByInn(Long inn);
    List listByDepartment(Department department);

}

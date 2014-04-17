package com.aimprosoft.department.service;

import com.aimprosoft.department.entity.Department;

public interface DepartmentService extends Service<Department>{
    Department getById(Integer id);
    Department getByName(String name);
}

package com.aimprosoft.department.dao;

import com.aimprosoft.department.entity.Department;

public interface DepartmentDao extends Dao<Department> {
    Department getByName(String name);
}

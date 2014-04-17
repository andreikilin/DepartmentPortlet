package com.aimprosoft.department.util;

import com.aimprosoft.department.entity.Department;
import com.aimprosoft.department.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.beans.PropertyEditorSupport;

@Component
public class DepartmentPropertyUtil extends PropertyEditorSupport {

    @Autowired
    DepartmentService departmentService;

    @Override
    public void setAsText(String departmentId) {
        Department department = departmentService.getById(Integer.parseInt(departmentId));
        this.setValue(department);
    }
}


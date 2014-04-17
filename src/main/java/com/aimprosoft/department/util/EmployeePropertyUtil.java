package com.aimprosoft.department.util;

import com.aimprosoft.department.entity.Employee;
import com.aimprosoft.department.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.beans.PropertyEditorSupport;

@Component
public class EmployeePropertyUtil extends PropertyEditorSupport {

    @Autowired
    EmployeeService employeeService;

    @Override
    public void setAsText(String employeeId) {
        Employee employee = employeeService.getById(Integer.parseInt(employeeId));
        this.setValue(employee);
    }
}


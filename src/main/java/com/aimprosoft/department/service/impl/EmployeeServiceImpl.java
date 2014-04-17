package com.aimprosoft.department.service.impl;

import com.aimprosoft.department.dao.Dao;
import com.aimprosoft.department.dao.EmployeeDao;
import com.aimprosoft.department.entity.Department;
import com.aimprosoft.department.entity.Employee;
import com.aimprosoft.department.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("EmployeeService")
@Transactional
public class EmployeeServiceImpl extends AbstractService<Employee> implements EmployeeService {

    @Autowired
    private EmployeeDao employeeDao;

    @Override
    protected Dao<Employee> getDao() {
        return employeeDao;
    }

    @Override
    public Employee getByEmail(String email) {
        return employeeDao.getByEmail(email);
    }

    @Override
    public Employee getByInn(Long inn) {
        return employeeDao.getByInn(inn);
    }

    @Override
    public List<Employee> listByDepartment(Department department) {
        return employeeDao.listByDepartment(department);
    }

//    @Override
//    public List<EmployeeJson> listJsonByDepartment(department department) {
//        List<EmployeeJson> employeeJsonList = new LinkedList<>();
//        List<employee> listByDepartment = employeeDao.listByDepartment(department);
//        for (employee employee : listByDepartment) {
//            EmployeeJson employeeJson = new EmployeeJson();
//            employeeJson.loadEmployee(employee);
//            employeeJsonList.add(employeeJson);
//        }
//        return employeeJsonList;
//    }
//
//    @Override
//    public List<EmployeeJson> listJson() {
//        List<EmployeeJson> employeeJsonList = new LinkedList<>();
//        for (employee employee : employeeDao.list()) {
//            EmployeeJson employeeJson = new EmployeeJson();
//            employeeJson.loadEmployee(employee);
//            employeeJsonList.add(employeeJson);
//        }
//        return employeeJsonList;
//    }
}

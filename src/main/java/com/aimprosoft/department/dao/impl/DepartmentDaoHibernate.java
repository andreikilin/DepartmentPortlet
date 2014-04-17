package com.aimprosoft.department.dao.impl;

import com.aimprosoft.department.dao.DepartmentDao;
import com.aimprosoft.department.entity.Department;
import org.springframework.stereotype.Repository;

@Repository
public class DepartmentDaoHibernate extends AbstractDaoHibernate<Department> implements DepartmentDao {

    public DepartmentDaoHibernate() {
        super(Department.class);
    }

    @Override
    public Department getByName(String name) {
        return (Department) getCurrentSession()
                .createQuery("from Department where name = :name")
                .setString("name", name).uniqueResult();
    }


}

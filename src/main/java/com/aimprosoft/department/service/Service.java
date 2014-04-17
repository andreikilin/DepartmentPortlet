package com.aimprosoft.department.service;

import com.aimprosoft.department.entity.BusinessEntity;

import java.util.List;

public interface Service<E extends BusinessEntity> {
    Integer add(E e);
    void update(E e);
    void delete(E e);
    E getById(Integer id);
    List<E> list();
}

package com.relations.all.repository.impl;

import com.relations.all.model.Employee;
import com.relations.all.repository.EmployeeCustomRepo;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public class EmployeeCustomRepoImpl implements EmployeeCustomRepo {

    @Autowired
    EntityManager entityManager;


    @Override
    public Page<Employee> dynamicFiltersWholeEntity(Pageable pageable, String name, Integer age, String sex, String city) {

        //Fixed part of the query
        String fixedPart = "select * from employee " +
                "where id in ( " +
                " select employees_id " +
                " from company_employees " +
                " where company_id = :companyId " +
                ")";

        return null;
    }
}

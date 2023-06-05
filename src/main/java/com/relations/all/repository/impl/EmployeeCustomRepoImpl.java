package com.relations.all.repository.impl;

import com.relations.all.model.Employee;
import com.relations.all.repository.EmployeeCustomRepo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Repository
public class EmployeeCustomRepoImpl implements EmployeeCustomRepo {

    @Autowired
    EntityManager entityManager;


    @Override
    public Page<Employee> dynamicFiltersWholeEntity(Pageable pageable, String companyId, String name, Integer age, String sex, String city) {

        //Fixed part of the query
        String fixedPart = "select * from employee " +
                "where id in ( " +
                " select employees_id " +
                " from company_employees " +
                " where company_id = :companyId " +
                ")";

        //Dynamic part of the query
        if(!ObjectUtils.isEmpty(name)){
            fixedPart += " AND name LIKE CONCAT('%', :name, '%') ";
        }

        //Create native query
        Query nativeQuery = entityManager.createNativeQuery(fixedPart);

        //Bind parameters

        //first bind path variable
        nativeQuery.setParameter("companyId", companyId);

        //now bind others
        if(!ObjectUtils.isEmpty(name)){
           nativeQuery.setParameter("name",name);
        }

        List resultList = nativeQuery.getResultList();

        return new PageImpl<>(resultList, pageable, 2);
    }
}

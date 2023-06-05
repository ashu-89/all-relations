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
    public Page<Employee> dynamicFiltersWholeEntityOptimized(Pageable pageable, String companyId, String name, Integer age, String sex, String city) {

        //Fixed part of the query
        String selectStatement = "select * from employee " ;

        String selectCountStatement = "select count(*) from employee ";

        String fixedPart =
                "where id in ( " +
                " select employees_id " +
                " from company_employees " +
                " where company_id = :companyId " +
                ")";



        //Dynamic part of the query
        if(!ObjectUtils.isEmpty(name)){
            fixedPart += " AND name LIKE CONCAT('%', :name, '%') ";
        }

        if(!ObjectUtils.isEmpty(age)){
            fixedPart += " AND age = :age ";
        }

        if(!ObjectUtils.isEmpty(sex)){
            fixedPart += " AND sex = :sex ";
        }

        if(!ObjectUtils.isEmpty(city)){
            fixedPart += " AND city = :city ";
        }

        //Create native query
        Query nativeQuery = entityManager.createNativeQuery( selectStatement + fixedPart);
        Query countQuery = entityManager.createNativeQuery(selectCountStatement + fixedPart);

        //Bind parameters

        //first bind path variable
        nativeQuery.setParameter("companyId", companyId);
        countQuery.setParameter("companyId", companyId);

        //now bind others
        if(!ObjectUtils.isEmpty(name)){
            nativeQuery.setParameter("name",name);
            countQuery.setParameter("name",name);
        }

        if(!ObjectUtils.isEmpty(age)){
            nativeQuery.setParameter("age",age);
            countQuery.setParameter("age",age);
        }

        if(!ObjectUtils.isEmpty(sex)){
            nativeQuery.setParameter("sex",sex);
            countQuery.setParameter("sex",sex);
        }

        if(!ObjectUtils.isEmpty(city)){
            nativeQuery.setParameter("city",city);
            countQuery.setParameter("city",city);
        }

        nativeQuery.setFirstResult(pageable.getPageNumber());
        nativeQuery.setMaxResults(pageable.getPageSize());

        List resultList = nativeQuery.getResultList();
        Long count = (Long) countQuery.getSingleResult();

        return new PageImpl<>(resultList, pageable, count);
    }
}

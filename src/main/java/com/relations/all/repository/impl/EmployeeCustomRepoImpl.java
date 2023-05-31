package com.relations.all.repository.impl;

import com.relations.all.dto.EmployeeDTO;
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

import java.util.ArrayList;
import java.util.List;

@Repository
public class EmployeeCustomRepoImpl implements EmployeeCustomRepo {

    @Autowired
    EntityManager entityManager;

    @Override
    public Page<Employee> findEmployeesByCompanyWithFilters(String companyId, Pageable pageable, Integer age, String sex, String city, String name) {

        // Fixed part of the query
        String query = "SELECT * FROM employee WHERE company_id = :companyId";

        // Dynamic part of the query
        if (!ObjectUtils.isEmpty(age)) {
            query += " AND age = :age";
        }
        if (!ObjectUtils.isEmpty(sex)) {
            query += " AND sex = :sex";
        }
        if (!ObjectUtils.isEmpty(city)) {
            query += " AND city = :city";
        }
        if (!ObjectUtils.isEmpty(name)) {
            query += " AND name = :name";
        }

        // Create native query
        Query countQuery = entityManager.createNativeQuery("SELECT COUNT(*) FROM (" + query + ") AS countQuery");
        Query dataQuery = entityManager.createNativeQuery(query, Employee.class);

        // Bind the parameters
        countQuery.setParameter("companyId", companyId);
        dataQuery.setParameter("companyId", companyId);

        if (!ObjectUtils.isEmpty(age)) {
            countQuery.setParameter("age", age);
            dataQuery.setParameter("age", age);
        }
        if (!ObjectUtils.isEmpty(sex)) {
            countQuery.setParameter("sex", sex);
            dataQuery.setParameter("sex", sex);
        }
        if (!ObjectUtils.isEmpty(city)) {
            countQuery.setParameter("city", city);
            dataQuery.setParameter("city", city);
        }
        if (!ObjectUtils.isEmpty(name)) {
            countQuery.setParameter("name", name);
            dataQuery.setParameter("name", name);
        }

        // Get the total count of results
        long totalCount = ((Number) countQuery.getSingleResult()).longValue();

        // Set pagination parameters
        dataQuery.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
        dataQuery.setMaxResults(pageable.getPageSize());

        // Get the result list
        List<Employee> employees = dataQuery.getResultList();

        return new PageImpl<>(employees, pageable, totalCount);
    }

    @Override
    public Page<EmployeeDTO> findEmployeesDTOByCompanyWithFilters(String companyId, Pageable pageable, Integer age, String sex, String city, String name) {

        // Fixed part
        String fixedPart = "select name, age, sex, city " +
                " from employee " +
                " where company_id = :companyId " ;


        // Dynamic part of the query
        if (!ObjectUtils.isEmpty(age)) {
            fixedPart += " AND age = :age";
        }
        if (!ObjectUtils.isEmpty(sex)) {
            fixedPart += " AND sex = :sex";
        }
        if (!ObjectUtils.isEmpty(city)) {
            fixedPart += " AND city = :city";
        }
        if (!ObjectUtils.isEmpty(name)) {
            fixedPart += " AND name = :name";
        }

        // Create the query & countQuery as well
        Query finalQuery = entityManager.createNativeQuery(fixedPart);
        Query countQuery = entityManager.createNativeQuery( "select count(*) from ( " + fixedPart + " ) as fixed " );


        // Bind params
        finalQuery.setParameter("companyId", companyId);
        countQuery.setParameter("companyId", companyId);

        if (!ObjectUtils.isEmpty(age)) {
            finalQuery.setParameter("age", age);
            countQuery.setParameter("age", age);
        }
        if (!ObjectUtils.isEmpty(sex)) {
            finalQuery.setParameter("sex", sex);
            countQuery.setParameter("sex", sex);
        }
        if (!ObjectUtils.isEmpty(city)) {
            finalQuery.setParameter("city", city);
            countQuery.setParameter("city", city);
        }
        if (!ObjectUtils.isEmpty(name)) {
            finalQuery.setParameter("name", name);
            countQuery.setParameter("name", name);
        }

        Long totalCount = (Long) countQuery.getSingleResult();

        // Set pagination
        finalQuery.setFirstResult(pageable.getPageNumber()* pageable.getPageSize());
        finalQuery.setMaxResults(totalCount.intValue());

        List<Object[]>  resultList = finalQuery.getResultList();

        List<EmployeeDTO> dtoList = new ArrayList<>();

        resultList.forEach(x -> {
            EmployeeDTO dto = new EmployeeDTO();
            dto.setName(x[0].toString());
            dto.setAge( Integer.valueOf(x[1].toString()));
            dto.setSex(x[2].toString());
            dto.setCity(x[3].toString());

            dtoList.add(dto);
        });


        // Return paginated result
        return new PageImpl<>(dtoList,pageable,totalCount);

    }
}

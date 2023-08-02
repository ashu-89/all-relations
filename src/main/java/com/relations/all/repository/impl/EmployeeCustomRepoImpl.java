package com.relations.all.repository.impl;

import com.relations.all.dto.EmployeeDTO;
import com.relations.all.model.Employee;
import com.relations.all.repository.EmployeeCustomRepo;
import com.relations.all.repository.EmployeeRepo; // Make sure to import your EmployeeRepo
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

    @Autowired
    EmployeeRepo employeeRepo; // Autowire your EmployeeRepo here

    @Override
    public Page<Employee> dynamicFiltersWholeEntityOptimized(Pageable pageable, String companyId, String name, Integer age, String sex, String city) {

        // Fixed part of the query
        String selectStatement = "select * from employee ";

        String selectCountStatement = "select count(*) from employee ";

        String fixedPart = " where company_id = :companyId ";

        // Dynamic part of the query
        if (!ObjectUtils.isEmpty(name)) {
            fixedPart += " AND name LIKE CONCAT('%', :name, '%') ";
        }

        if (!ObjectUtils.isEmpty(age)) {
            fixedPart += " AND age = :age ";
        }

        if (!ObjectUtils.isEmpty(sex)) {
            fixedPart += " AND sex = :sex ";
        }

        if (!ObjectUtils.isEmpty(city)) {
            fixedPart += " AND city = :city ";
        }

        // Create native query
        Query nativeQuery = entityManager.createNativeQuery(selectStatement + fixedPart);
        Query countQuery = entityManager.createNativeQuery(selectCountStatement + fixedPart);

        // Bind parameters
        // First bind path variable
        nativeQuery.setParameter("companyId", companyId);
        countQuery.setParameter("companyId", companyId);

        // Now bind others
        if (!ObjectUtils.isEmpty(name)) {
            nativeQuery.setParameter("name", name);
            countQuery.setParameter("name", name);
        }

        if (!ObjectUtils.isEmpty(age)) {
            nativeQuery.setParameter("age", age);
            countQuery.setParameter("age", age);
        }

        if (!ObjectUtils.isEmpty(sex)) {
            nativeQuery.setParameter("sex", sex);
            countQuery.setParameter("sex", sex);
        }

        if (!ObjectUtils.isEmpty(city)) {
            nativeQuery.setParameter("city", city);
            countQuery.setParameter("city", city);
        }

        nativeQuery.setFirstResult(pageable.getPageNumber());
        nativeQuery.setMaxResults(pageable.getPageSize());

        List resultList = nativeQuery.getResultList();
        Long count = (Long) countQuery.getSingleResult();

        return new PageImpl<>(resultList, pageable, count);
    }

    @Override
    public Page<EmployeeDTO> dynamicFiltersDTO(Pageable pageable, String companyId, String name, Integer age, String sex, String city) {

        String selectHead = "Select * ";
        String selectCountHead = "Select count(*) ";

        String fixedPart = " from employee where company_id = :companyId ";

        if (!ObjectUtils.isEmpty(name)) {
            fixedPart += " AND name like CONCAT( '%', :name, '%') ";
        }

        if (!ObjectUtils.isEmpty(age)) {
            fixedPart += " AND age = :age ";
        }

        if (!ObjectUtils.isEmpty(sex)) {
            fixedPart += " AND sex = :sex ";
        }

        if (!ObjectUtils.isEmpty(city)) {
            fixedPart += " AND city = :city ";
        }

        // Create paginatedQuery and CountQuery
        Query paginatedQuery = entityManager.createNativeQuery(selectHead + fixedPart);
        Query countQuery = entityManager.createNativeQuery(selectCountHead + fixedPart);

        // Bind parameters

        // Mandatory params
        paginatedQuery.setParameter("companyId", companyId);
        countQuery.setParameter("companyId", companyId);

        // Filters
        if (!ObjectUtils.isEmpty(name)) {
            paginatedQuery.setParameter("name", name);
            countQuery.setParameter("name", name);
        }

        if (!ObjectUtils.isEmpty(age)) {
            paginatedQuery.setParameter("age", age);
            countQuery.setParameter("age", age);
        }

        if (!ObjectUtils.isEmpty(sex)) {
            paginatedQuery.setParameter("sex", sex);
            countQuery.setParameter("sex", sex);
        }

        if (!ObjectUtils.isEmpty(city)) {
            paginatedQuery.setParameter("city", city);
            countQuery.setParameter("city", city);
        }

        // Set offset and limit for paginated query
        paginatedQuery.setFirstResult(pageable.getPageNumber());
        paginatedQuery.setMaxResults(pageable.getPageSize());

        List<Object[]> resultList = paginatedQuery.getResultList();

        long count = (Long) countQuery.getSingleResult();

        List<EmployeeDTO> employeeDTOList = new ArrayList<>();

        resultList.forEach(x -> {
            EmployeeDTO dto = new EmployeeDTO();
            dto.setName(x[4].toString());
            dto.setAge((Integer) x[0]);
            dto.setSex(x[5].toString());
            dto.setCity(x[3].toString());

            employeeDTOList.add(dto);
        });

        return new PageImpl<>(employeeDTOList, pageable, count);
    }
}

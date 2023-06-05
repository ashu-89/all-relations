package com.relations.all.repository;

import com.relations.all.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EmployeeCustomRepo {


    Page<Employee> dynamicFiltersWholeEntity(Pageable pageable, String companyId, String name, Integer age, String sex, String city);
    public Page<Employee> dynamicFiltersWholeEntityOptimized(Pageable pageable, String companyId, String name, Integer age, String sex, String city);
}

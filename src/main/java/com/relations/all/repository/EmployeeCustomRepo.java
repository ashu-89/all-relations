package com.relations.all.repository;

import com.relations.all.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EmployeeCustomRepo {

    Page<Employee> findEmployeesByCompanyWithFilters(String companyId, Pageable pageable, Integer age, String sex, String city, String name);
}

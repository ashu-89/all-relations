package com.relations.all.repository;

import com.relations.all.model.Company;
import com.relations.all.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface EmployeeRepo extends JpaRepository<Employee, UUID> {

    @Query( value = "select * from employee where company_id = :companyId ",
    countQuery = "select count(*) from employee where company_id = :companyId",
    nativeQuery = true)
    Page<Employee> findEmployeesByCompany(String companyId, Pageable pageable);
}

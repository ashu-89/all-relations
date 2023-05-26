package com.relations.all.repository;

import com.relations.all.model.Company;
import com.relations.all.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CompanyRepo extends JpaRepository<Company, UUID> {
    //Page<Employee> getAllEmployees(Pageable pageable);

}

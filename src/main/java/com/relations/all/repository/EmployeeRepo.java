package com.relations.all.repository;

import com.relations.all.model.Company;
import com.relations.all.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EmployeeRepo extends JpaRepository<Employee, UUID> {
}

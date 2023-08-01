package com.relations.all.repository;

import com.relations.all.model.Company;
import com.relations.all.model.Passport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PassportRepo extends JpaRepository<Passport, UUID> {
    //Page<Employee> getAllEmployees(Pageable pageable);

}

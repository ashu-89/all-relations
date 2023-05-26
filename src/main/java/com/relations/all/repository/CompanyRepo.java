package com.relations.all.repository;

import com.relations.all.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CompanyRepo extends JpaRepository<Company, UUID> {
}

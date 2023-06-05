package com.relations.all.repository;

import com.relations.all.model.Company;
import com.relations.all.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, UUID> {

    @Query(value = "select * from employee " +
            "where id in ( " +
            " select employees_id " +
            " from company_employees " +
            " where company_id = :companyId " +
            ")",
            countQuery = "select count(*) from employee " +
                    " where id in ( " +
                    " select employees_id " +
                    " from company_employees " +
                    " where company_id = :companyId " +
                    " )",
            nativeQuery = true
    )
    Page<Employee> findEmployeesByCompanyWithPagination(String companyId, Pageable pageable);

}



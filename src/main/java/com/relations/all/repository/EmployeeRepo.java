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

//    @Query(value = "select * from employee " +
//            " where company_id = :companyId ",
//            countQuery = "select count(*) from employee " +
//                    " where company_id = :companyId ",
//            nativeQuery = true
//    )
//    Page<Employee> findEmployeesByCompanyWithPagination(String companyId, Pageable pageable);

    @Query(value = "select e from Employee e join fetch e.passport " +
            "where e.company.id = :companyId",
            countQuery = "select count(e) from Employee e " +
                    "where e.company.id = :companyId")
    Page<Employee> findEmployeesByCompanyWithPassport(UUID  companyId, Pageable pageable);


}



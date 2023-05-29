package com.relations.all.service;

import com.relations.all.model.Employee;
import com.relations.all.repository.EmployeeRepoCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepoCustom employeeRepoCustom;
    public Page<Employee> findEmployessByCompanyId(String companyId, Pageable pageable) {
        return employeeRepoCustom.findEmployeesByCompanyWithPagination(companyId,pageable);
    }
}

package com.relations.all.service;

import com.relations.all.Exception.RelationsException;
import com.relations.all.dto.EmployeeDTO;
import com.relations.all.model.Employee;
import com.relations.all.repository.EmployeeRepo;
import com.relations.all.repository.EmployeeCustomRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    @Autowired
    EmployeeCustomRepo employeeCustomRepo;

    @Autowired
    EmployeeRepo employeeRepo;

    public Page<Employee> findEmployessByCompanyId(String companyId, Pageable pageable) {
        return employeeRepo.findEmployeesByCompanyWithPagination(companyId,pageable);
    }

    public Page<Employee> dynamicFiltersWholeEntity(Pageable pageable, String companyId, String name, Integer age, String sex, String city)
     throws RelationsException {
        return employeeCustomRepo.dynamicFiltersWholeEntityOptimized(pageable, companyId, name, age, sex, city);
    }

    public Page<EmployeeDTO> dynamicFiltersDTO(Pageable pageable, String companyId, String name, Integer age, String sex, String city) {
        return employeeCustomRepo.dynamicFiltersDTO(pageable, companyId, name, age, sex, city);

    }
}

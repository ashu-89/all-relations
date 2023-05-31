package com.relations.all.service;

import com.relations.all.dto.EmployeeDTO;
import com.relations.all.model.Employee;
import com.relations.all.repository.EmployeeCustomRepo;
import com.relations.all.repository.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepo employeeRepo;

    @Autowired
    EmployeeCustomRepo employeeCustomRepo;

    public Page<Employee> findEmployeesByCompany(String companyId, Pageable pageable) {
        return employeeRepo.findEmployeesByCompany(companyId,pageable);
    }

    public Page<Employee> findEmployeesByCompanyWithFilters(String companyId, Pageable pageable, Integer age, String sex, String city, String name) {
        return employeeCustomRepo.findEmployeesByCompanyWithFilters(companyId,pageable,age,sex,city,name);
    }

    public Page<EmployeeDTO> findEmployeesDTOByCompanyWithFilters(String companyId, Pageable pageable, Integer age, String sex, String city, String name) {
        return employeeCustomRepo.findEmployeesDTOByCompanyWithFilters(companyId,pageable,age,sex,city,name);

    }

}

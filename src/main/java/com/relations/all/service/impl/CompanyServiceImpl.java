package com.relations.all.service.impl;

import com.relations.all.model.Company;
import com.relations.all.model.Employee;
import com.relations.all.repository.CompanyRepo;
import com.relations.all.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    CompanyRepo companyRepo;

//    @Override
//    public Page<Employee> getAllEmployees(PageRequest pageRequest) {
//        return companyRepo.getAllEmployees(pageRequest);
//    }
}

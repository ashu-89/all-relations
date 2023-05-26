package com.relations.all.controller;

import com.relations.all.model.Company;
import com.relations.all.model.Employee;
import com.relations.all.repository.CompanyRepo;
import com.relations.all.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class CompanyController {

    @Autowired
    CompanyService companyService;
    @Autowired
    private CompanyRepo companyRepo;

    @GetMapping("/company/{id}/employees")
    public ResponseEntity<List<Employee>> getAllEmployees(
            @PathVariable(name = "id", required = true) UUID uuid,
            @RequestParam(name = "pageNumber", required = false) Integer pageNumber,
            @RequestParam(name = "pageSize", required = false) Integer pageSize) throws Exception {

        //PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);

        //Page<Employee> result = companyService.getAllEmployees(pageRequest);

        Company company = companyRepo.findById(uuid)
                .orElseThrow( () -> new Exception("Invalid uuid"));

        return new ResponseEntity<>(company.getEmployees(), HttpStatus.OK);


    }


}

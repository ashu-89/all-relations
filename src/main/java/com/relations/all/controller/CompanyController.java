package com.relations.all.controller;

import com.relations.all.Exception.RelationsException;
import com.relations.all.model.Company;
import com.relations.all.model.Employee;
import com.relations.all.model.Product;
import com.relations.all.repository.CompanyRepo;
import com.relations.all.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.Set;
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

    @GetMapping("/company/{id}/products")
    public ResponseEntity<Set<Product>> getAllProducts(
            @PathVariable(name = "id", required = true) UUID uuid,
            @RequestParam(name = "pageNumber", required = false) Integer pageNumber,
            @RequestParam(name = "pageSize", required = false) Integer pageSize) throws Exception {

        //PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);

        //Page<Employee> result = companyService.getAllEmployees(pageRequest);

        Company company = companyRepo.findById(uuid)
                .orElseThrow( () -> new Exception("Invalid uuid"));

        return new ResponseEntity<>(company.getProducts(), HttpStatus.OK);


    }

//    @GetMapping(path = "/company/{companyId}/products")
//    public ResponseEntity<Set<Product>> getCompaniesByProductId(@PathVariable ("companyId") UUID companyId,
//                                                                @RequestParam(value = "pageNo", required = false) Integer pageNo,
//                                                                @RequestParam(value = "pageSize", required = false) Integer pageSize) throws RelationsException {
//
//
//        if (ObjectUtils.isEmpty(pageNo) || pageNo < 0) {
//            pageNo = 0;
//        }
//
//        if (ObjectUtils.isEmpty(pageSize) || pageSize < 0) {
//            pageSize = 10;
//        }
//
//        Pageable pageable = PageRequest.of(pageNo, pageSize);
//
//        Company company = companyRepo.findById(companyId)
//                .orElseThrow( () -> new RelationsException("invalid productId") );
//
//        return new ResponseEntity<>( company.getProducts(), HttpStatus.OK  );
//
//
//
//
//    }


}

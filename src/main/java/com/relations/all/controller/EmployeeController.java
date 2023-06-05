package com.relations.all.controller;

import com.relations.all.Exception.RelationsException;
import com.relations.all.model.Employee;
import com.relations.all.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @GetMapping("/employees-by-company-id/{companyId}")
    public ResponseEntity<Page<Employee>> getEmployeesByCompanyId(@PathVariable("companyId") String companyId,
                                                                  @RequestParam(value = "pageNo", required = false) int pageNo,
                                                                  @RequestParam(value = "pageSize", required = false) int pageSize
                                                                  ) throws Exception {

        try{
            Pageable pageable = PageRequest.of(pageNo,pageSize);
            Page<Employee> pagedResponse =  employeeService.findEmployessByCompanyId(companyId, pageable);
            return new ResponseEntity<>(pagedResponse, HttpStatus.OK);
        } catch (Exception e){
            throw new Exception("couldn't fetch employees of this company"  + e.getMessage());
        }
    }

    @GetMapping("/dynamic-filters-whole-entity/{companyId}")
    public ResponseEntity<Page<Employee>> dynamicFiltersWholeEntity( @PathVariable("companyId") String companyId,
     @RequestParam(value = "pageNo", required = false) Integer pageNo,
     @RequestParam(value = "pageSize", required = false) Integer pageSize,
     @RequestParam(value = "name", required = false) String name,
     @RequestParam(value = "age", required = false) Integer age,
     @RequestParam(value = "sex", required = false) String sex,
     @RequestParam(value = "city", required = false) String city ) throws Exception {

        try {

            if (ObjectUtils.isEmpty(pageNo) || pageNo < 0) {
                pageNo = 0;
            }

            if (ObjectUtils.isEmpty(pageSize) || pageSize < 0) {
                pageSize = 10;
            }

            Pageable pageable = PageRequest.of(pageNo, pageSize);

            Page<Employee> pagedResult = employeeService.dynamicFiltersWholeEntity(pageable, companyId, name, age, sex, city);

            return new ResponseEntity<>(pagedResult, HttpStatus.OK);

        } catch (RelationsException e){
            throw e;
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }

    }
}

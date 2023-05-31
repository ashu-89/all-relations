package com.relations.all.controller;

import com.relations.all.dto.SearchFilter;
import com.relations.all.model.Employee;
import com.relations.all.service.EmployeeService;
import org.apache.tomcat.util.buf.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @GetMapping("/by-company/{companyId}")
    public ResponseEntity<Page<Employee>> findEmployeesByCompany( @PathVariable("companyId") String companyId,
                @RequestParam(value = "pageNo", required = false) Integer pageNo,
                @RequestParam(value = "pageSize", required = false) Integer pageSize,
                @RequestParam(value = "age", required = false) Integer age,
                @RequestParam(value = "sex", required = false) String sex,
                @RequestParam(value = "city", required = false) String city,
                @RequestParam(value = "name", required = false) String name ) {

        try{
                if(ObjectUtils.isEmpty(pageNo) || pageNo < 0)
                    pageNo=0;

                if(ObjectUtils.isEmpty(pageSize))
                    pageSize=10;

            Pageable pageable = PageRequest.of(pageNo,pageSize);

            //Page<Employee> paginatedResult = employeeService.findEmployessByCompany(companyId, pageable);
            Page<Employee> paginatedResult = employeeService.findEmployessByCompanyWithFilters(companyId, pageable,
                    age, sex, city, name);

            return new ResponseEntity<>(paginatedResult, HttpStatus.OK);

        } catch (Exception e){
            throw e;
        }

    }


}

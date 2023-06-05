package com.relations.all;

import com.relations.all.model.Company;
import com.relations.all.model.Employee;
import com.relations.all.repository.CompanyRepo;
import com.relations.all.repository.EmployeeRepo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class StartRunner implements ApplicationRunner {

    /* Add whatever Bean you need here and autowire them through the constructor or with @Autowired */
    @Autowired
    CompanyRepo companyRepo;

    @Autowired
    EmployeeRepo employeeRepo;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // Do whatever you need here inside

        //Generate companies
        Company google = new Company();
        google.setName("Google");
        companyRepo.save(google);
        System.out.println("Company: " + google + " " + "UUID: " + google.getId());

        Company facebook = new Company();
        facebook.setName("Facebook");
        companyRepo.save(facebook);
        System.out.println("Facebook: " + facebook + " " + "UUID: " + facebook.getId());

        //Generate Employees
        Employee e1 = new Employee();
        e1.setName("Ashu");
        e1.setAge(34);
        e1.setCity("Bangalore");
        e1.setSex("male");
        employeeRepo.save(e1);

        Employee e2 = new Employee();
        e2.setName("Abhi");
        e2.setCity("Bangalore");
        e2.setSex("male");
        e2.setAge(29);
        employeeRepo.save(e2);

        Employee e3 = new Employee();
        e3.setName("Martha");
        e3.setAge(42);
        e3.setCity("Los Angeles");
        e3.setSex("female");
        employeeRepo.save(e3);

        Employee e4 = new Employee();
        e4.setName("Sundari");
        e4.setCity("Los Angeles");
        e4.setSex("female");
        e4.setAge(35);
        employeeRepo.save(e4);

        List<Employee> googleEmployees = new ArrayList<>();
        googleEmployees.add(e1);
        googleEmployees.add(e4);
        google.setEmployees(googleEmployees);
        companyRepo.save(google);

        List<Employee> faceBookEmployees = new ArrayList<>();
        faceBookEmployees.add(e2);
        faceBookEmployees.add(e3);
        facebook.setEmployees(faceBookEmployees);
        companyRepo.save(facebook);




    }


}
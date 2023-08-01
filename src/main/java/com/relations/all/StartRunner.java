package com.relations.all;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.relations.all.model.Company;
import com.relations.all.model.Employee;
import com.relations.all.model.Passport;
import com.relations.all.model.Product;
import com.relations.all.repository.CompanyRepo;
import com.relations.all.repository.EmployeeRepo;
import com.relations.all.repository.PassportRepo;
import com.relations.all.repository.ProductRepo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.*;

@Component
@AllArgsConstructor
public class StartRunner implements ApplicationRunner {

    /* Add whatever Bean you need here and autowire them through the constructor or with @Autowired */
    @Autowired
    CompanyRepo companyRepo;

    @Autowired
    EmployeeRepo employeeRepo;

    @Autowired
    ProductRepo productRepo;

    @Autowired
    PassportRepo passportRepo;

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
        e1.setCompany(google);

        Passport ashuzPpt = new Passport();
        ashuzPpt.setDateIssued(LocalDate.of(2020,8,1));
        ashuzPpt.setDateOfExpiry(LocalDate.of(2023,8,1));
        ashuzPpt.setPassportId("IND-001");
        ashuzPpt.setName("Pathak, Ashutosh");

        passportRepo.save(ashuzPpt);


        e1.setPassport(ashuzPpt);

        employeeRepo.save(e1);
        ashuzPpt.setEmployee(e1);
        passportRepo.save(ashuzPpt);

        Employee e2 = new Employee();
        e2.setName("Abhi");
        e2.setCity("Bangalore");
        e2.setSex("male");
        e2.setAge(29);
        e2.setCompany(facebook);

        Passport abhizPpt = new Passport();
        abhizPpt.setDateIssued(LocalDate.of(2018,8,1));
        abhizPpt.setDateOfExpiry(LocalDate.of(2020,8,1));
        abhizPpt.setPassportId("IND-002");
        abhizPpt.setName("Pathak, Abhishek");
        passportRepo.save(abhizPpt);

        e2.setPassport(abhizPpt);

        employeeRepo.save(e2);

        abhizPpt.setEmployee(e2);
        passportRepo.save(abhizPpt);

        Employee e3 = new Employee();
        e3.setName("Martha");
        e3.setAge(42);
        e3.setCity("Los Angeles");
        e3.setSex("female");
        e3.setCompany(google);

        Passport marthazPpt = new Passport();
        marthazPpt.setDateIssued(LocalDate.of(2020,5,1));
        marthazPpt.setDateOfExpiry(LocalDate.of(2023,5,1));
        marthazPpt.setPassportId("US-001");
        marthazPpt.setName("Sanchez, Martha");
        passportRepo.save(marthazPpt);

        e3.setPassport(marthazPpt);

        employeeRepo.save(e3);

        marthazPpt.setEmployee(e3);
        passportRepo.save(marthazPpt);

        Employee e4 = new Employee();
        e4.setName("Sundari");
        e4.setCity("Los Angeles");
        e4.setSex("female");
        e4.setAge(35);
        e4.setCompany(facebook);

        //
        Passport sundariPpt = new Passport();
        sundariPpt.setDateIssued(LocalDate.of(2020,6,1));
        sundariPpt.setDateOfExpiry(LocalDate.of(2023,6,1));
        sundariPpt.setPassportId("US-002");
        sundariPpt.setName("Pichai, Sundari");
        passportRepo.save(sundariPpt);

        e4.setPassport(sundariPpt);
        //

        employeeRepo.save(e4);

        sundariPpt.setEmployee(e4);
        passportRepo.save(sundariPpt);

        List<Employee> googleEmployees = new ArrayList<>();
        googleEmployees.add(e1);
        googleEmployees.add(e3);
        google.setEmployees(googleEmployees);
        companyRepo.save(google);

        List<Employee> faceBookEmployees = new ArrayList<>();
        faceBookEmployees.add(e2);
        faceBookEmployees.add(e4);
        facebook.setEmployees(faceBookEmployees);
        companyRepo.save(facebook);

        Product email = new Product();
        email.setName("email");

        Product post = new Product();
        post.setName("post");

        Product search = new Product();
        search.setName("search");


        Set<Company> companyThatSellEmails = new HashSet<>();
        companyThatSellEmails.add(google);
        companyThatSellEmails.add(facebook);

        email.setCompanies(companyThatSellEmails);
        productRepo.save(email);

        Set<Company> companiesThatSellPost = new HashSet<>();
        companiesThatSellPost.add(facebook);

        post.setCompanies(companiesThatSellPost);
        productRepo.save(post);

        Set<Company> companiesThatSellSearch = new HashSet<>();
        companiesThatSellSearch.add(google);

        search.setCompanies(companiesThatSellSearch);
        productRepo.save(search);

        Set<Product> productsSoldByGoogle = new HashSet<>();
        productsSoldByGoogle.add(email);
        productsSoldByGoogle.add(search);
        google.setProducts(productsSoldByGoogle);
        companyRepo.save(google);

        Set<Product> productsSoldByFacebook = new HashSet<>();
        productsSoldByFacebook.add(email);
        productsSoldByFacebook.add(post);
        facebook.setProducts(productsSoldByFacebook);
        companyRepo.save(facebook);

        String json =
                //"{\"id\":2,\"itemName\":\"book\",\"owner\":{\"id\":1,\"name\":\"John\",\"userItems\":[2]}}";
        "{\n" +
                "        \"id\": \"7fe0f703-fa57-4037-b790-4d94b2cfe062\",\n" +
                "        \"name\": \"Facebook\",\n" +
                "        \"products\": [\n" +
                "            {\n" +
                "                \"id\": \"4754b2b7-3c3b-489d-89b2-0c5ccc73370e\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"id\": \"b14f1e70-d82a-4032-a41a-4faabdc78e57\"\n" +
                "            }\n" +
                "        ]\n" +
                "    }";

        Company company = new ObjectMapper().readerFor(Company.class).readValue(json);

        company.getProducts().forEach(x -> System.out.println(x.getId()));

        System.out.println(company.getId());
        System.out.println(company.getName());




    }


}
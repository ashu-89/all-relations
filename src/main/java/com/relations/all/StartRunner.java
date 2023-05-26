package com.relations.all;

import com.relations.all.model.Company;
import com.relations.all.repository.CompanyRepo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class StartRunner implements ApplicationRunner {

    /* Add whatever Bean you need here and autowire them through the constructor or with @Autowired */
    @Autowired
    CompanyRepo companyRepo;
    @Override
    public void run(ApplicationArguments args) throws Exception {
        // Do whatever you need here inside
        Company google = new Company();
        google.setName("Google");
        companyRepo.save(google);

        System.out.println("Company: " + google + " " + "UUID: " + google.getId());
    }


}
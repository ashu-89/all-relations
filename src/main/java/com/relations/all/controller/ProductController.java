package com.relations.all.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.relations.all.Exception.RelationsException;
import com.relations.all.dto.CompanyDTO;
import com.relations.all.dto.ProductDTO;
import com.relations.all.model.Company;
import com.relations.all.model.Product;
import com.relations.all.repository.ProductRepo;
import com.relations.all.service.ProductService;
import com.relations.all.views.Views;
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

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@RestController
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping(path = "/products/{productId}/companies", produces="application/json")
    public ResponseEntity<String> getCompaniesByProductId(@PathVariable ("productId") UUID productId,
                                                                   @RequestParam(value = "pageNo", required = false) Integer pageNo,
                                                                   @RequestParam(value = "pageSize", required = false) Integer pageSize) throws RelationsException, JsonProcessingException {


        if (ObjectUtils.isEmpty(pageNo) || pageNo < 0) {
            pageNo = 0;
        }

        if (ObjectUtils.isEmpty(pageSize) || pageSize < 0) {
            pageSize = 10;
        }

        Pageable pageable = PageRequest.of(pageNo, pageSize);

        Product product = productService.findCompaniesByProductId(productId, pageable )
                .orElseThrow( () -> new RelationsException("invalid productId") );

//        Set<CompanyDTO> responseSet = new HashSet<>();
//
//        product.getCompanies().forEach(x -> {
//            CompanyDTO dto = new CompanyDTO();
//            dto.setId(x.getId());
//            dto.setName(x.getName());
//            responseSet.add(dto);
//        });

        String result = new ObjectMapper().writerWithView(Views.Public.class)
                .writeValueAsString(product.getCompanies());


        return new ResponseEntity<>( result, HttpStatus.OK  );




    }
}

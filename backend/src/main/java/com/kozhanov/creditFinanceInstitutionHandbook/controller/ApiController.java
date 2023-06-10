package com.kozhanov.creditFinanceInstitutionHandbook.controller;

import com.kozhanov.creditFinanceInstitutionHandbook.repository.codeValue.AccountOperationRestrictionRepository;
import com.kozhanov.creditFinanceInstitutionHandbook.repository.codeValue.AccountStatusRepository;
import com.kozhanov.creditFinanceInstitutionHandbook.repository.xml.BICDirectoryEntryRepository;
import com.kozhanov.creditFinanceInstitutionHandbook.until.FilterParameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import springfox.documentation.spring.web.json.Json;

import java.util.*;

@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    private BICDirectoryEntryRepository bicDirectoryEntryRepository;

    @GetMapping("/data")
    public ResponseEntity<?> getAllData() {
        System.out.println(bicDirectoryEntryRepository.findAll());

        return new ResponseEntity<>(bicDirectoryEntryRepository.findAll(), HttpStatus.OK);
    }

    @PostMapping("/filter")
    public ResponseEntity<?> filter(@RequestBody FilterParameters filterParameters) {
        System.out.println(filterParameters);
        return new ResponseEntity<>("Все четка filter", HttpStatus.OK);
    }


}

package com.kozhanov.creditFinanceInstitutionHandbook.controller;

import com.kozhanov.creditFinanceInstitutionHandbook.until.FilterParameters;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import springfox.documentation.spring.web.json.Json;

import java.util.*;

@RestController
@RequestMapping("/api")
public class ApiController {


    @GetMapping("/data")
    public ResponseEntity<?> getAllData() {
        System.out.println("qq есть запрос");

        return new ResponseEntity<>("Все четка data", HttpStatus.OK);
    }

    @PostMapping("/filter")
    public ResponseEntity<?> filter(@RequestBody FilterParameters filterParameters) {
        System.out.println(filterParameters);
        return new ResponseEntity<>("Все четка filter", HttpStatus.OK);
    }


}

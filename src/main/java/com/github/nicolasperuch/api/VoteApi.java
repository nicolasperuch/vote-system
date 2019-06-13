package com.github.nicolasperuch.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.ResponseEntity.ok;

@RestController
public class VoteApi {

    @GetMapping
    public ResponseEntity<?> sayHi(){
        return ok("hi");
    }
}

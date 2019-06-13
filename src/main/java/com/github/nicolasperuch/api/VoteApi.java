package com.github.nicolasperuch.api;

import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("vote")
public class VoteApi {

    @ApiOperation(value = "This is a hi endpoint!")
    @GetMapping
    public ResponseEntity<?> sayHi(){
        return ok("hi");
    }
}

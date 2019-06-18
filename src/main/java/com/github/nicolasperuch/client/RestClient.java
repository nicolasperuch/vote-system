package com.github.nicolasperuch.client;

import com.github.nicolasperuch.client.dto.CpfValidationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import static org.springframework.http.HttpMethod.GET;

@Component
public class RestClient {

    @Autowired
    private RestTemplate restTemplate;
    private final String BASE_CPF_VALIDATION_URI = "https://user-info.herokuapp.com/users/";

    public ResponseEntity<CpfValidationResponse> checkIfCpfIsAbleToVote(String cpf){
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<?> entity = new HttpEntity<>(headers);
        return restTemplate.exchange(buildUrl(cpf), GET, entity, CpfValidationResponse.class);
    }

    public String buildUrl(String cpf){
        return BASE_CPF_VALIDATION_URI + cpf;
    }
}
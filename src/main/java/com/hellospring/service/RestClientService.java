package com.hellospring.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RestClientService {

    private static final Logger log = LoggerFactory.getLogger(RestClientService.class);

    @Autowired
    private RestTemplate restTemplate;

    public <T, U> ResponseEntity<T> post(String url, U body, HttpHeaders headers, Class<T> responseType){
        HttpEntity<U> requestEntity = new HttpEntity<>(body, headers);
        ResponseEntity<T> responseEntity = restTemplate.postForEntity(url, requestEntity, responseType);
        log.info("POST url {} called and status code {}", url,responseEntity.getStatusCodeValue());
        return responseEntity;
    }

    public <T> ResponseEntity<T> get(String url, Class<T> responseType){
        ResponseEntity<T> responseEntity = restTemplate.getForEntity(url,responseType);
        log.info("GET url {} called and status code{}", url, responseEntity.getStatusCodeValue());
        return responseEntity;
    }
}

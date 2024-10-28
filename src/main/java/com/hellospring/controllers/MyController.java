package com.hellospring.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

    private static final Logger log = LoggerFactory.getLogger(MyController.class);

    @GetMapping(value = "/greet")
    public String greet(@RequestParam(name = "user_name") String userName){
        log.info("MyController greet api called {}", userName );
        return "Hello World " + userName;
    }
}

package com.brandon.securitydemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class HelloController {

    @GetMapping("/hello")
    public String Hello() {
        return "Hello Spring Security";
    }

    @GetMapping("/index")
    public String index() {
        return "hello index";
    }

    @GetMapping("/sales")
    public String sales() {
        return "hello sales";
    }
}

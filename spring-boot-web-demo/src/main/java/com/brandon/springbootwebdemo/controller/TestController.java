package com.brandon.springbootwebdemo.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class TestController {

    @GetMapping("/he")
    public String hello() {
        return "greeting/hello";
    }
}

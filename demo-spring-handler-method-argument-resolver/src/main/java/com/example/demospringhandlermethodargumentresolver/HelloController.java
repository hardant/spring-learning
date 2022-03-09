package com.example.demospringhandlermethodargumentresolver;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloController {
    @GetMapping
    public String hello(@UserAnnotation User user) {
        return String.format("Hello %s\r\n", user.getUserName());
    }

}

package com.brandon.securitydemo.controller;

import com.brandon.securitydemo.entity.User;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/test")
public class HelloController {

    @GetMapping("/hello")
    public String Hello() {
        return "Hello Spring Security";
    }

    // 这个注解给予角色，需要提供一个字符串数组来指定角色
    //@Secured({"ROLE_sales1", "admins"})
    // Spring-EL expression to be evaluated before invoking the protected method
    //@PreAuthorize("hasAnyAuthority('admins')")
    // Spring-EL expression to be evaluated before invoking the protected method
    @PostAuthorize("hasAnyAuthority('admins')")
    @GetMapping("/index")
    public String index() {
        return "hello index";
    }


    @PostMapping("/allUsers")
    @PreFilter("filterObject.id==1") // 对参数进行过滤
    @PreAuthorize("permitAll()")
    public String allUsers(@RequestBody List<User> users) {
        for (User user : users) {
            System.out.println(user.getId());
        }
        return "success";
    }

    @GetMapping("/allUsers")
    @PreAuthorize("permitAll()")
    @PostFilter("filterObject.id==1") // 对返回值进行过滤
    public List<User> getAllUser() {
        User user1 = new User();
        User user2 = new User();


        List<User> users = new ArrayList<>();

        user1.setId(1);
        user2.setId(2);

        users.add(user1);
        users.add(user2);
        return users;
    }

    @GetMapping("/sales")
    public String sales() {
        return "hello sales";
    }
}

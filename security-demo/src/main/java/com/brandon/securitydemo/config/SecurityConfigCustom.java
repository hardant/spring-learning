package com.brandon.securitydemo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 用自定义UserDetailsService实现类的方式只需要想spring容器注册一个UserDetailsService类的bean就可以了
 */
@Configuration
public class SecurityConfigCustom extends WebSecurityConfigurerAdapter {
    /*
    @Autowired
    private UserDetailsService userDetailsService;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(createPasswordEncoder());
    }
    @Override
    protected UserDetailsService userDetailsService() {
        return userDetailsService;
    }
    */

    /**
     * 如果没有配置这个bean登陆过程会抛出如下异常
     * java.lang.IllegalArgumentException: There is no PasswordEncoder mapped for the id "null"
     * 原因是这个encoder对象没有被注册到spring容器中。登陆过程中找不到对应的bean进行加密验证
     * @return
     */
    @Bean
    PasswordEncoder createPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

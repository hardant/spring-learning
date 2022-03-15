package com.brandon.securitydemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        String password = passwordEncoder.encode("123");

        auth.inMemoryAuthentication().withUser("Lucy").password(password).roles("admin");
    }

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

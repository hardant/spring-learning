package com.brandon.securitydemo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
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
     * antMatchers配置  {@code ant-style-path} 对应的访问权限 {@code ant-style-pattern} 参考
     * {@link org.springframework.util.AntPathMatcher}
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin() //自定义自己编写的登陆页面
                .loginPage("/login.html") // 登陆访问页面
                .loginProcessingUrl("/user/login") // 登陆访问路径 这个接口不需要自己写。只是让框架配置
                .defaultSuccessUrl("/test/index") // 登陆成功后，跳转路径
                .and().authorizeRequests()
                    // 允许下面的路径访问。不进行用户/权限认证
                    .antMatchers("/", "/test/hello", "/user/login.html", "/login.html").permitAll()
                .anyRequest().authenticated() // 其他的要进行用户认证

                .and().csrf().disable();// 关闭csrf

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

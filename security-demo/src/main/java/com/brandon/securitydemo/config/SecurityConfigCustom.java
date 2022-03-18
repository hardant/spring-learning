package com.brandon.securitydemo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

/**
 * 用自定义UserDetailsService实现类的方式只需要想spring容器注册一个UserDetailsService类的bean就可以了
 */
@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class SecurityConfigCustom extends WebSecurityConfigurerAdapter {

    private final DataSource dataSource;
    private UserDetailsService userDetailsService;

    public SecurityConfigCustom(DataSource dataSource, UserDetailsService userDetailsService) {
        this.dataSource = dataSource;
        this.userDetailsService = userDetailsService;
    }


    /**
     * 配置token持久化库
     * @return
     */
    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);
        // 这个配置智能执行一次，二次执行会因为数据表已经存在二报错
        //jdbcTokenRepository.setCreateTableOnStartup(true);
        return jdbcTokenRepository;
    }
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
                .loginPage("/login") // 登陆访问页面
                .loginProcessingUrl("/user/login") // 登陆访问路径 这个接口不需要自己写。只是让框架配置
                .defaultSuccessUrl("/loginSuccess.html") // 登陆成功后，跳转路径
                .and().authorizeRequests()
                    // 允许下面的路径访问。不进行用户/权限认证
                    .antMatchers("/", "/test/hello", "/login").permitAll()
                    // 有管理员权限才
                // 可以访问
                    //.antMatchers("/test/index").hasAuthority("admins")
                    .antMatchers("/test/sales").hasRole("sales")
                    .anyRequest().authenticated() // 其他的要进行用户认证
                .and()
                    .rememberMe()
                    .tokenRepository(persistentTokenRepository())
                    .tokenValiditySeconds(60)
                    .userDetailsService(userDetailsService);
        //http.csrf().disable();
        http.exceptionHandling().accessDeniedPage("/unauthorized.html");

        http.logout().logoutUrl("/logout").logoutSuccessUrl("/login.html");
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

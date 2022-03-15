package com.brandon.securitydemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userDetailsService")
public class MyUserDetailService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if("Mary".equals(username)) {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

            String password = passwordEncoder.encode("123");
            List<GrantedAuthority> auths = AuthorityUtils.commaSeparatedStringToAuthorityList("User,Admin");
            return new User("Mary", password, auths);
        }
        return null;
    }
}

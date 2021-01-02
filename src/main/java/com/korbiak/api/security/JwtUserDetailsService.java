package com.korbiak.api.security;

import com.korbiak.api.model.Employee;
import com.korbiak.api.security.jwt.JwtUserFactory;
import com.korbiak.api.service.EmployeeService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
@Data
@Slf4j
public class JwtUserDetailsService implements UserDetailsService {

    private final EmployeeService employeeService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("Loading user by name = " + username);
        String[] names = username.split("_");
        Employee user = employeeService.findUserByName(names[0], names[1]);
        return JwtUserFactory.create(user);
    }
}

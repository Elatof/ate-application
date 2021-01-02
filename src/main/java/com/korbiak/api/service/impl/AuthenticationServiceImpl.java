package com.korbiak.api.service.impl;


import com.korbiak.api.dto.input.AuthenticationRequest;
import com.korbiak.api.model.Employee;
import com.korbiak.api.security.jwt.JwtTokenProvider;
import com.korbiak.api.service.AuthenticationService;
import com.korbiak.api.service.EmployeeService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;


@Service
@Data
@Slf4j
public class AuthenticationServiceImpl implements AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final EmployeeService employeeService;
    private BCryptPasswordEncoder encoder;

    @PostConstruct
    private void initEncoder() {
        encoder = new BCryptPasswordEncoder();
    }

    @Override
    public String login(AuthenticationRequest authenticationRequest) {
        log.info("Logging user with name = " + authenticationRequest.getFirstName());
        Employee user = employeeService.findUserByName(authenticationRequest.getFirstName(),
                authenticationRequest.getSecondName());
        if (!encoder.matches(authenticationRequest.getPassword(), user.getPassword())) {
            log.error("Password not correct");
            throw new IllegalArgumentException("Password not correct");
        }
        return jwtTokenProvider.createToken(user);
    }
}

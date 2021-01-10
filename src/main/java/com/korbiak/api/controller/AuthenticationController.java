package com.korbiak.api.controller;

import com.korbiak.api.dto.input.AuthenticationRequest;
import com.korbiak.api.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("ate-api/authentication/")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("login")
    public String login(@Validated @RequestBody AuthenticationRequest authenticationRequest) {
        return authenticationService.login(authenticationRequest);
    }
}

package com.korbiak.api.service;

import com.korbiak.api.dto.input.AuthenticationRequest;

public interface AuthenticationService {

    String login(AuthenticationRequest authenticationRequest);
}

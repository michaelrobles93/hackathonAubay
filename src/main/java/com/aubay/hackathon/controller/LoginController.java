package com.aubay.hackathon.controller;

import com.aubay.hackathon.model.request.Credential;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static com.aubay.hackathon.security.SecurityConstants.SIGN_IN_URL;

@RestController
@RequestMapping(SIGN_IN_URL)
public class LoginController {

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void login(@RequestBody @Valid Credential credential) {

    }
}

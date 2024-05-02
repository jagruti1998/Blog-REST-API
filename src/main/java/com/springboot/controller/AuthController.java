package com.springboot.controller;

import com.springboot.dtos.JWTAuthResponse;
import com.springboot.dtos.LoginDto;
import com.springboot.dtos.RegisterDto;
import com.springboot.service.AuthService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    //build login rest api
    @PostMapping(value = {"login","/signin"})
    public ResponseEntity<JWTAuthResponse> login(LoginDto loginDto){//changed string to JWTAUTHResponse in jWT
        String token= authService.login(loginDto); //changed response to token in jwt

        JWTAuthResponse jwtAuthResponse=new JWTAuthResponse();
        jwtAuthResponse.setAccessToken(token);
        return ResponseEntity.ok(jwtAuthResponse);
    }

    //build registered rest api
    @PostMapping(value = {"/register","/signin"})
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto){
        String response=authService.register(registerDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}

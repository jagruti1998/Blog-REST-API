package com.springboot.controller;

import com.springboot.dtos.LoginDto;
import com.springboot.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
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
    public ResponseEntity<String> login(LoginDto loginDto){
        String response= authService.login(loginDto);
        return ResponseEntity.ok(response);
    }
}

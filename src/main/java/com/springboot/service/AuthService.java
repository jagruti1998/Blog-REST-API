package com.springboot.service;

import com.springboot.dtos.LoginDto;
import com.springboot.dtos.RegisterDto;

public interface AuthService {

    String login(LoginDto loginDto);

    String register(RegisterDto registerDto);
}

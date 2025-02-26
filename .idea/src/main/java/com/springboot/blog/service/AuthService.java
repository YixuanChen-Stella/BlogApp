package com.springboot.blog.service;

import com.springboot.blog.dto.LoginDto;
import com.springboot.blog.dto.SignUpDto;

public interface AuthService {
    String login(LoginDto loginDto);

    String register(SignUpDto signUpDto);
}

package com.AjoPay.AjoPay.security;

import com.AjoPay.AjoPay.service.serviceImplementations.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration

@RequiredArgsConstructor
@Slf4j
public class SecurityConfig  {
    private final UserDetailsServiceImpl userDetailsService;
    private final PasswordEncoder passwordEncoder;



}

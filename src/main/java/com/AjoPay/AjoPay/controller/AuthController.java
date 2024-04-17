package com.AjoPay.AjoPay.controller;

import com.AjoPay.AjoPay.dto.requestDto.ForgotPasswordDTo;
import com.AjoPay.AjoPay.service.serviceInterface.AuthService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/forgot-password-request")
    public ResponseEntity<?> passwordRequestReset(@RequestBody ForgotPasswordDTo forgotPasswordDTo){
        String str = authService.passWordRequest(forgotPasswordDTo);
        return new ResponseEntity<>(str, HttpStatusCode.valueOf(HttpStatus.SC_OK));
    }
}

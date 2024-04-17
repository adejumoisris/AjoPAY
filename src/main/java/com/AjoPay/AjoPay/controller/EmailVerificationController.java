package com.AjoPay.AjoPay.controller;

import com.AjoPay.AjoPay.entity.VerificationToken;
import com.AjoPay.AjoPay.service.serviceInterface.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api-verify")
@RequiredArgsConstructor
public class EmailVerificationController {
    private final UsersService usersService;
    @GetMapping("/account")
    public ResponseEntity<String> verifyAccount(@RequestParam("token") String token){
        usersService.verifyAccount(token);
        return ResponseEntity.ok("Email verified. Account activated ");
    }
}

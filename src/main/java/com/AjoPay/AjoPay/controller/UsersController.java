package com.AjoPay.AjoPay.controller;

import com.AjoPay.AjoPay.dto.requestDto.UsersRequest;
import com.AjoPay.AjoPay.dto.responseDto.UsersResponse;
import com.AjoPay.AjoPay.service.serviceInterface.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/user")
public class UsersController {
    private final UsersService usersService;
    @PostMapping(path = "/users")
    public UsersResponse createFreeAccount(@RequestBody  UsersRequest usersRequest ){
        return usersService.createFreeAccount(usersRequest);
    }
}

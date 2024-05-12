package com.AjoPay.AjoPay.controller;

import com.AjoPay.AjoPay.dto.requestDto.CreditRequest;
import com.AjoPay.AjoPay.dto.requestDto.TransferRequest;
import com.AjoPay.AjoPay.dto.requestDto.UsersRequest;
import com.AjoPay.AjoPay.dto.responseDto.AppResponse;
import com.AjoPay.AjoPay.dto.responseDto.TransferResponse;
import com.AjoPay.AjoPay.service.serviceInterface.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/user")
public class UsersController {

    public final UsersService usersService;
    // Done Testing
    @CrossOrigin
    @PostMapping(path = "/register")
    public AppResponse createFreeAccount(@RequestBody @Valid UsersRequest usersRequest ){
        return usersService.createFreeAccount(usersRequest);
    }

    // Done testing
    @CrossOrigin
    @PutMapping (path = "{id}/users")
    public AppResponse updatedUserProfile(@PathVariable("id") Long id, @RequestBody UsersRequest request) {

        return usersService.updatedUserProfile(id, request);
    }

    // Done testing

    @PostMapping("transfer")
    public TransferResponse transfer(@RequestBody TransferRequest request){
        return usersService.transfer(request);
    }

    @PostMapping("credit")
    public TransferResponse CreditAccount (@RequestBody CreditRequest request){
        return usersService.CreditAccount(request);

    }
}

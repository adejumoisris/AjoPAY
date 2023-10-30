package com.AjoPay.AjoPay.controller;

import com.AjoPay.AjoPay.dto.requestDto.BvnVeriRequestDto;
import com.AjoPay.AjoPay.service.serviceInterface.AppVerificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/app")
public class AppVerificationController {
    private final AppVerificationService appVerificationService;
    @PostMapping(path = "/verify")
    public void saveAppVerification(@RequestBody BvnVeriRequestDto bvnVeriRequestDto){
        appVerificationService.saveAppVerification(bvnVeriRequestDto);
    }
}

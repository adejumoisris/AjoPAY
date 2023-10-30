package com.AjoPay.AjoPay.controller;

import com.AjoPay.AjoPay.dto.requestDto.BvnVeriRequestDto;
import com.AjoPay.AjoPay.dto.responseDto.BvnResponseDto;
import com.AjoPay.AjoPay.service.serviceInterface.BvnVerificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/bvn-verification")
public class BvnVerificationController {

    private final BvnVerificationService bvnVerificationService;

    @PostMapping("/verify")
    public ResponseEntity<BvnResponseDto> verifyBvn (@RequestBody BvnVeriRequestDto bvnVeriRequestDto){
        BvnResponseDto responseDto = bvnVerificationService.veryBvn(bvnVeriRequestDto);
        return ResponseEntity.ok(responseDto);
    }

}

package com.AjoPay.AjoPay.service.serviceInterface;

import com.AjoPay.AjoPay.dto.requestDto.BvnVeriRequestDto;

public interface AppVerificationService {
    // saving the app verification request into the database

    void saveAppVerification(BvnVeriRequestDto bvnVeriRequestDto);
}

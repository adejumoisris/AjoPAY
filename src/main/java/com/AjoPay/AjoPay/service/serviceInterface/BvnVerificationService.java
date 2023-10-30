package com.AjoPay.AjoPay.service.serviceInterface;

import com.AjoPay.AjoPay.dto.requestDto.BvnVeriRequestDto;
import com.AjoPay.AjoPay.dto.responseDto.BvnResponseDto;

public interface BvnVerificationService {

    public BvnResponseDto veryBvn (BvnVeriRequestDto bvnVeriRequestDto);

}

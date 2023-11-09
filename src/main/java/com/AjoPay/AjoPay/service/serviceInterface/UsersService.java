package com.AjoPay.AjoPay.service.serviceInterface;

import com.AjoPay.AjoPay.dto.requestDto.TransferRequest;
import com.AjoPay.AjoPay.dto.requestDto.UsersRequest;
import com.AjoPay.AjoPay.dto.responseDto.AppResponse;
import com.AjoPay.AjoPay.dto.responseDto.TransferResponse;

public interface UsersService {
    public AppResponse createFreeAccount(UsersRequest request);

    public AppResponse updatedUserProfile(Long Id, UsersRequest request);

    public TransferResponse transfer(TransferRequest request);
}

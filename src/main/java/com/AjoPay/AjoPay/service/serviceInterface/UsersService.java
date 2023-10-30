package com.AjoPay.AjoPay.service.serviceInterface;

import com.AjoPay.AjoPay.dto.requestDto.UsersRequest;
import com.AjoPay.AjoPay.dto.responseDto.UsersResponse;

public interface UsersService {
    public UsersResponse createFreeAccount(UsersRequest request);
}

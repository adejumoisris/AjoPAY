package com.AjoPay.AjoPay.service.serviceInterface;

import com.AjoPay.AjoPay.dto.requestDto.CreditRequest;
import com.AjoPay.AjoPay.dto.requestDto.ForgotPasswordDTo;
import com.AjoPay.AjoPay.dto.requestDto.TransferRequest;
import com.AjoPay.AjoPay.dto.requestDto.UsersRequest;
import com.AjoPay.AjoPay.dto.responseDto.AppResponse;
import com.AjoPay.AjoPay.dto.responseDto.TransferResponse;
import com.AjoPay.AjoPay.entity.Users;
import com.AjoPay.AjoPay.entity.VerificationToken;

public interface UsersService {
    public AppResponse createFreeAccount(UsersRequest request);
    String verifyAccount(String token);

//    public void verifyEmail(String token);

    public AppResponse updatedUserProfile(Long Id, UsersRequest request);

    public TransferResponse transfer(TransferRequest request);

    public TransferResponse CreditAccount(CreditRequest request);
     String fetchUserAndEnable(VerificationToken verificationToken);

   Users findByEmail(String mail);




}

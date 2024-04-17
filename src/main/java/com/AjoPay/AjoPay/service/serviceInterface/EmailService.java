package com.AjoPay.AjoPay.service.serviceInterface;

import com.AjoPay.AjoPay.dto.requestDto.EmailDetails;
import com.AjoPay.AjoPay.entity.Users;

import java.util.concurrent.CompletableFuture;

public interface EmailService {
    CompletableFuture<String> sendSimpleMail(EmailDetails emailDetails);

     String sendMailWithAttachment(EmailDetails emailDetails);

     // sending verification Email;



     // sending forgot password

     void sendForgotPasswordEmail(Users  users, String otp) throws Exception;


}

package com.AjoPay.AjoPay.service.serviceInterface;

import com.AjoPay.AjoPay.dto.requestDto.EmailDetails;

public interface EmailService {
     String sendSimpleMail(EmailDetails emailDetails);

     String sendMailWithAttachment(EmailDetails emailDetails);


}

package com.AjoPay.AjoPay.service.serviceImplementations;

import com.AjoPay.AjoPay.dto.requestDto.EmailDetails;
import com.AjoPay.AjoPay.dto.requestDto.UsersRequest;
import com.AjoPay.AjoPay.dto.responseDto.UsersResponse;
import com.AjoPay.AjoPay.entity.Users;
import com.AjoPay.AjoPay.repository.UsersRepository;
import com.AjoPay.AjoPay.service.serviceInterface.EmailService;
import com.AjoPay.AjoPay.service.serviceInterface.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsersServiceImpl implements UsersService {
    private final UsersRepository usersRepository;
    private final EmailService emailService;
    @Override
    public UsersResponse createFreeAccount(UsersRequest usersRequest) {
        Users newUsers = Users.builder()
                .firstName(usersRequest.getFirstName())
                .lastName(usersRequest.getLastName())
                .emailAddress(usersRequest.getEmailAddress())
                .phoneNumber(usersRequest.getPhoneNumber())
                .passWord(usersRequest.getPassWord())

                .build() ;

        Users saveUser = usersRepository.save(newUsers);
        // sending mail to the user personal email that his/her account is created

        EmailDetails emailDetails = EmailDetails.builder()
                .recipient(usersRequest.getEmailAddress())
                .subject("signing Up")
                .messageBody( saveUser.getFirstName() +  " Congratulation you have successfully signup verify your account  ")
                .build();
        emailService.sendSimpleMail(emailDetails);

        return UsersResponse.builder()
                .firstName(saveUser.getFirstName())
                .emailAddress(saveUser.getEmailAddress())
                .build();
    }
}

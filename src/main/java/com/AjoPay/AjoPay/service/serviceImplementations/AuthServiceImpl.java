package com.AjoPay.AjoPay.service.serviceImplementations;

import com.AjoPay.AjoPay.dto.requestDto.ForgotPasswordDTo;
import com.AjoPay.AjoPay.entity.Users;
import com.AjoPay.AjoPay.exception.CustomException;
import com.AjoPay.AjoPay.repository.UsersRepository;
import com.AjoPay.AjoPay.security.RandomGenerator;
import com.AjoPay.AjoPay.service.serviceInterface.AuthService;
import com.AjoPay.AjoPay.service.serviceInterface.EmailService;
import com.AjoPay.AjoPay.service.serviceInterface.UsersService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {
    private final UsersService usersService;
    private final UsersRepository usersRepository;
    private final EmailService emailService;

    @Override
    public String passWordRequest(ForgotPasswordDTo forgotPasswordDTo) {
        log.info(" Sending Email for password reset ");

        Users users = usersService.findByEmail(forgotPasswordDTo.getEmail());
        if (users == null) {
            throw new CustomException("Email not found");
        }


        String otp = RandomGenerator.generateOtp();
        users.setOtp(otp);
        users.setOtpCreatedAt(new Date().getTime());
        usersRepository.save(users);

        try {
            log.info("otp -> {}", otp);
            emailService.sendForgotPasswordEmail(users,otp);
        }catch (Exception e){
            log.info("Error sending forgot password mail : {}", e.getMessage());
            e.printStackTrace();
        }
        log.info("email sent successfully");

        return "Reset password mail successfully";
    }
}

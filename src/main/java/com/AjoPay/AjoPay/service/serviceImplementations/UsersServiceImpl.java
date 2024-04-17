package com.AjoPay.AjoPay.service.serviceImplementations;

import com.AjoPay.AjoPay.dto.requestDto.*;
import com.AjoPay.AjoPay.dto.responseDto.AccountInfo;
import com.AjoPay.AjoPay.dto.responseDto.AppResponse;
import com.AjoPay.AjoPay.dto.responseDto.TransferResponse;
import com.AjoPay.AjoPay.dto.responseDto.UserResponse;
import com.AjoPay.AjoPay.entity.Users;
import com.AjoPay.AjoPay.entity.VerificationToken;
import com.AjoPay.AjoPay.enums.TransactionType;
import com.AjoPay.AjoPay.enums.UserStatus;
import com.AjoPay.AjoPay.exception.CustomException;
import com.AjoPay.AjoPay.repository.UsersRepository;
import com.AjoPay.AjoPay.repository.VerificationTokenRepository;
import com.AjoPay.AjoPay.service.serviceInterface.EmailService;
import com.AjoPay.AjoPay.service.serviceInterface.TransactionService;
import com.AjoPay.AjoPay.service.serviceInterface.UsersService;
import com.AjoPay.AjoPay.utils.AppUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.annotation.Schedules;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

import static com.AjoPay.AjoPay.security.TokenGenerator.generateVerificationToken;

@Service
@RequiredArgsConstructor
@Slf4j
public class UsersServiceImpl implements UsersService {
    private final UsersRepository usersRepository;
    private final JavaMailSender mailSender;
    private final VerificationTokenRepository verificationTokenRepository;
    private final EmailService emailService;
    private final TransactionService transactionService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public AppResponse createFreeAccount(UsersRequest usersRequest) {

        /**
         * creating an account is usually creating a new user into DB
         * is by instatiating a new user using builder patter
         * check if user already has an account
         */
        // << checking if the user ALlready Exist in the database

        if (usersRepository.existsByEmail(usersRequest.getEmail())){
            return AppResponse.builder()
                    .responseCode(AppUtils.ACCOUNT_EXISTS_CODE)
                    .responseMessage(AppUtils.ACCOUNT_EXISTS_MESSAGE)
                    .userResponse(null)
                    .build();
        }


        Users newUsers = Users.builder()
                .firstName(usersRequest.getFirstName())
                .lastName(usersRequest.getLastName())
                .email(usersRequest.getEmail())
                .phoneNumber(usersRequest.getPhoneNumber())
                .passWord((usersRequest.getPassWord()))
                .saltPassWord(passwordEncoder.encode(usersRequest.getPassWord()))
                .accountBalance(BigDecimal.ZERO)
                // generating random AccountNumber
                .accountNumber(AppUtils.generateAccountNumber())
                .status(UserStatus.PENDING)
                .build() ;


        Users saveUser = usersRepository.save(newUsers);
        // sending mail to the user personal email that his/her account is created
        // sending verification Email to user to convert his status from PENDING to ACTIVE

        // this is working on another thread
        sendRegistrationConfirmationEmail(newUsers, usersRequest.getEmail());
        return AppResponse.builder()
                .responseCode(AppUtils.ACCOUNT_CREATION_SUCCESS)
                .responseMessage(AppUtils.ACCOUNT_CREATION_MESSAGE)
                .userResponse(UserResponse.builder()
                        .firstName(usersRequest.getFirstName())
                        .email(usersRequest.getEmail())
                        .status(UserStatus.valueOf(String.valueOf(saveUser.getStatus())))
                        .build())
                .build();
    }


    // send registration confirmation Email

    private void sendRegistrationConfirmationEmail(Users users, String email){
        String token = generateVerificationToken(users);
        EmailDetails emailDetails = EmailDetails.builder()
                .recipient(email)
                .subject(" Activate your Account")
                .messageBody("Thank you for Creating your account with us " +
                        "please click on the link below to activate your account : " +
                        "http://localhost:8080/api-verify/account" + token)
                .build();
        emailService.sendSimpleMail(emailDetails);
    }


    // generate Token against the user email and save in the database
    private String generateVerificationToken(Users users){
        log.info("inside generateVerificationToken, generating token for {}", users.getEmail());

        String token = UUID.randomUUID().toString();
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setUsers(users);
        verificationToken.setToken(token);

        log.info("saving token to database ");
        verificationTokenRepository.save(verificationToken);
        return token;

    }

    // verify users



    @Override
    public String verifyAccount(String token) {
        Optional<VerificationToken> verificationToken = verificationTokenRepository.findByToken(token);
        if (verificationToken.isPresent()){
            Boolean isVerified = verificationToken.get().getUsers().getIsVerified();
            if (isVerified){
                throw new CustomException("token used please request another validation token ", HttpStatus.BAD_REQUEST);
            }
            return fetchUserAndEnable(verificationToken.get());
        }
        throw new CustomException(" invalid token");
    }







      // verifying Email

//    public void verifyEmail(String token){
//        // Logic to verify the token, update user status to 'active', etc.
//
//        Users = usersRepository.findByVerificationToken(token);
//        // if the user is not empty(is not equal to null) get user status and verify if its PENDING
//        if (users != null && users.getStatus() == UserStatus.PENDING){
//
//            // update user status to active and clear the verification
//            users.setStatus(UserStatus.ACTIVE);
//            usersRepository.save(users);
//        }else {
//            log.error("Invalid  email ");
//            throw new CustomException("Invalid User ");
//        }
//
//    }

    // fetchUser and Enable

    public String fetchUserAndEnable(VerificationToken verificationToken){
        Users users = verificationToken.getUsers();
        if (users != null && users.getStatus()== UserStatus.PENDING){
            users.setStatus(UserStatus.ACTIVE);
            usersRepository.save(users);
        }else {
            log.error("invalid token");
            throw new CustomException("Invalid token");
        }

        return "Account Verify successfully";

    }


    @Override
    public AppResponse updatedUserProfile(Long Id, UsersRequest request) {

        Users userUpdated = usersRepository.findById(Id).get();

        userUpdated.setFirstName(request.getFirstName());
        userUpdated.setPassWord(request.getPassWord());

        Users saveUpdated = usersRepository.save(userUpdated);



        return AppResponse.builder()
                .responseCode(AppUtils.User_Updated_SUCCESS)
                .responseMessage(AppUtils.User_Updated_Message)
                .userResponse(UserResponse.builder()
                        .firstName(saveUpdated.getFirstName())
                        .email(saveUpdated.getEmail())
                        .build())
                .build();
    }

    // Transferring money into another account
    // Transferring Money into an Account
    // Transfer Account (money to another account)



    @Scheduled(cron = "0 0 12 28 * ?")  // Fire at 12 PM on the 28th day of each month
    @Override
    public TransferResponse transfer(TransferRequest request) {

        /**
         * process of doing transfer from one Account to another Account
         * 1. get the account to debit (check if it exists )
         * 2.check if the amount i am debiting is more than the current balance
         * 3. debit the account
         * 4. get the account to credit         *  the  credit account
         */

        // to check id the destination account Exist

        boolean isDestinationAccountExist = usersRepository.existsByAccountNumber(request.getDestinationAccountNumber());
        // if its  destination account number does not exist

        if (!isDestinationAccountExist){
            return TransferResponse.builder()
                    .responseCode(AppUtils.ACCOUNT_NOT_EXIST_CODE)
                    .responseMessage(AppUtils.ACCOUNT_NOT_EXIST_MESSAGE)
                    .accountInfo(null)
                    .build();
        }

        Users sourceAccountUser = usersRepository.findByAccountNumber(request.getSourceAccountNumber());
        // check if u have sufficient Amount of money to transfer

        if (request.getAmount().compareTo(sourceAccountUser.getAccountBalance()) > 0){
            return TransferResponse.builder()
                    .responseCode(AppUtils.INSUFFICIENT_BALANCE_CODE)
                    .responseMessage(AppUtils.INSUFFICIENT_BALANCE_MESSAGE)
                    .accountInfo(null)
                    .build();
        }

        // if there is available money perform the debit deduction
        // this is where we are performing the deduction

        sourceAccountUser.setAccountBalance(sourceAccountUser.getAccountBalance().subtract(request.getAmount()));
        String sourceUserName = sourceAccountUser.getFirstName() + " " + sourceAccountUser.getLastName();

        usersRepository.save(sourceAccountUser);
        // sending debit  alert to the sourceAccount account

        EmailDetails debitAlert = EmailDetails.builder()
                .subject("DEBIT ALERT")
                .recipient(sourceAccountUser.getEmail())
                .messageBody(" The sum of " + request.getAmount() + " as being deducted from your account! Your account account balance is  " + sourceAccountUser.getAccountBalance())
                .build();
        emailService.sendSimpleMail(debitAlert);

        // crediting the destination account


        Users destinationAccountUser = usersRepository.findByAccountNumber(request.getDestinationAccountNumber());
        destinationAccountUser.setAccountBalance(destinationAccountUser.getAccountBalance().add(request.getAmount()));

        //        String recipientUserName = destinationAccountUser.getFirstName() + " " + destinationAccountUser.getLastName() + " " + destinationAccountUser.getOtherName();

        usersRepository.save(destinationAccountUser);

        // sending credit  alert to the destinationAccount account

        EmailDetails CreditAlerts = EmailDetails.builder()
                .subject("Credit Alert")
                .recipient(sourceAccountUser.getEmail())
                .messageBody(" The sum of " + request.getAmount() + " as being sent to your account from " + sourceUserName + " your Current Balance is " + sourceAccountUser.getAccountBalance())
                .build();

        emailService.sendSimpleMail(CreditAlerts);

        TransactionDto transactionDto = TransactionDto.builder()
                .accountNumber(destinationAccountUser.getAccountNumber())
                .transactionType("DEBIT")
                .amount(request.getAmount())
                .build();

        transactionService.saveTransaction(transactionDto);

        return TransferResponse.builder()
                .responseCode(AppUtils.TRANSFER_SUCCESSFUL_CODE)
                .responseMessage(AppUtils.TRANSFER_SUCCESSFUL_MESSAGE)
                .accountInfo(null)
                .build();
    }

    @Override
    public TransferResponse CreditAccount(CreditRequest request) {
        // checking if the account Exist
        boolean isAccountExist = usersRepository.existsByAccountNumber(request.getAccountNumber());
        if (!isAccountExist){
            return TransferResponse.builder()
                    .responseCode(AppUtils.ACCOUNT_NOT_EXIST_CODE)
                    .responseMessage(AppUtils.ACCOUNT_NOT_EXIST_MESSAGE)
                    .accountInfo(null)
                    .build();
        }

        // getiing user to credit



            Users userToCredit = usersRepository.findByAccountNumber(request.getAccountNumber());
            // user to credit
            // process of crediting user account
            userToCredit.setAccountBalance(userToCredit.getAccountBalance().add(request.getAmount()));
            usersRepository.save(userToCredit);

            // saving Transaction




        TransactionDto transactionDto = TransactionDto.builder()
                .accountNumber(userToCredit.getAccountNumber())
                .transactionType("CREDIT")
                .amount(request.getAmount())
                .build();
        transactionService.saveTransaction(transactionDto);
        return TransferResponse.builder()
                .responseCode(AppUtils.ACCOUNT_CREDITED_SUCCESSFULLY)
                .responseMessage(AppUtils.ACCOUNT_CREDITED_SUCCESS_MESSAGE)
                .accountInfo(AccountInfo.builder()
                        .accountName(userToCredit.getFirstName() + " " + userToCredit.getLastName())
                        .accountBalance(userToCredit.getAccountBalance())
                        .accountNUmber(userToCredit.getAccountNumber())

                        .build())
                .build();
    }
    // fi

    @Override
    public Users findByEmail(String mail) {
        return usersRepository.findByEmail(mail).orElseThrow(
                ()-> {
                    throw new CustomException(" user not found");
                }
        );

    }

    // resetting password




    // crediting Account







}

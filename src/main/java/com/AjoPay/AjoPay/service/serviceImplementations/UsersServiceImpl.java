package com.AjoPay.AjoPay.service.serviceImplementations;

import com.AjoPay.AjoPay.dto.requestDto.EmailDetails;
import com.AjoPay.AjoPay.dto.requestDto.TransactionDto;
import com.AjoPay.AjoPay.dto.requestDto.TransferRequest;
import com.AjoPay.AjoPay.dto.requestDto.UsersRequest;
import com.AjoPay.AjoPay.dto.responseDto.AppResponse;
import com.AjoPay.AjoPay.dto.responseDto.TransferResponse;
import com.AjoPay.AjoPay.dto.responseDto.UserResponse;
import com.AjoPay.AjoPay.entity.Users;
import com.AjoPay.AjoPay.enums.TransactionType;
import com.AjoPay.AjoPay.repository.UsersRepository;
import com.AjoPay.AjoPay.service.serviceInterface.EmailService;
import com.AjoPay.AjoPay.service.serviceInterface.TransactionService;
import com.AjoPay.AjoPay.service.serviceInterface.UsersService;
import com.AjoPay.AjoPay.utils.AppUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UsersServiceImpl implements UsersService {
    private final UsersRepository usersRepository;
    private final EmailService emailService;
    private final TransactionService transactionService;
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
                .passWord(usersRequest.getPassWord())

                .build() ;

        Users saveUser = usersRepository.save(newUsers);
        // sending mail to the user personal email that his/her account is created

        EmailDetails emailDetails = EmailDetails.builder()
                .recipient(usersRequest.getEmail())
                .subject("signing Up")
                .messageBody( saveUser.getFirstName() +  " Congratulation you have successfully signup verify your account  ")
                .build();
        emailService.sendSimpleMail(emailDetails);

        return AppResponse.builder()
                .responseCode(AppUtils.ACCOUNT_CREATION_SUCCESS)
                .responseMessage(AppUtils.ACCOUNT_CREATION_MESSAGE)
                .userResponse(UserResponse.builder()
                        .firstName(usersRequest.getFirstName())
                        .email(usersRequest.getEmail())
                        .build())
                .build();
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



}

package com.AjoPay.AjoPay.utils;

import java.time.Year;

public class AppUtils {
    public static final String ACCOUNT_EXISTS_CODE = "001";
    public static final String ACCOUNT_EXISTS_MESSAGE = "This user already have account created!";
    public static final String ACCOUNT_CREATION_SUCCESS = "002";
    public static final String ACCOUNT_CREATION_MESSAGE = " Account as being successfully created!";
    public static final String User_Updated_SUCCESS = "003";
    public static final String User_Updated_Message = " user Updated Successfully ";
    public static final String ACCOUNT_NOT_EXIST_CODE = "003";
    public static  final String ACCOUNT_NOT_EXIST_MESSAGE = "User with the provided account Number does not exist";
    public static  final String INSUFFICIENT_BALANCE_CODE = "006";
    public static final String INSUFFICIENT_BALANCE_MESSAGE = "Insufficient Balance";
    public static final String  TRANSFER_SUCCESSFUL_CODE ="008";
    public static final String  TRANSFER_SUCCESSFUL_MESSAGE= "Transfer Successful";
    public static final String ACCOUNT_CREDITED_SUCCESSFULLY = "005";
    public static final String ACCOUNT_CREDITED_SUCCESS_MESSAGE = "User credited account";


    // writing a method that generate Account details

    public static String generateAccountNumber(){
        long currentYear = System.currentTimeMillis();

        int min = 100000;
        int max = 999999;

        int randNumber = (int) Math.floor(Math.random() * (max -min +1) +min);

        String year = String.valueOf(currentYear);
        String randomNumber = String.valueOf(randNumber);

        StringBuilder accountNumber = new StringBuilder();
        return accountNumber.append(year).append(randomNumber).toString();

    }


}

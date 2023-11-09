package com.AjoPay.AjoPay.service.serviceInterface;

import com.AjoPay.AjoPay.dto.requestDto.TransactionDto;

public interface TransactionService {
    void saveTransaction(TransactionDto transactionDto);
}

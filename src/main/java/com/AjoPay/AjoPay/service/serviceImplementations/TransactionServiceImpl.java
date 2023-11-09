package com.AjoPay.AjoPay.service.serviceImplementations;

import com.AjoPay.AjoPay.dto.requestDto.TransactionDto;
import com.AjoPay.AjoPay.entity.Transaction;
import com.AjoPay.AjoPay.repository.TransactionRepository;
import com.AjoPay.AjoPay.service.serviceInterface.TransactionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;
    @Override
    public void saveTransaction(TransactionDto transactionDto) {
        Transaction transaction = Transaction.builder()
                .transactionType(transactionDto.getTransactionType())
                .accountNumber(transactionDto.getAccountNumber())
                .amount(transactionDto.getAmount())
                .status("SUCCESS")
                .build();

        transactionRepository.save(transaction);

        System.out.println(" Transaction save Successfully");


    }
}

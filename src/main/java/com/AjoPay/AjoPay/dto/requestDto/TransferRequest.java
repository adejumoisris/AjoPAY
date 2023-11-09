package com.AjoPay.AjoPay.dto.requestDto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
@Getter
@Setter
@Builder
public class TransferRequest {
    private String sourceAccountNumber;
    private String destinationAccountNumber;
    private BigDecimal amount;
}

package com.AjoPay.AjoPay.dto.requestDto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreditRequest {
    private String accountNumber;
    private BigDecimal amount;
}

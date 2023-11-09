package com.AjoPay.AjoPay.dto.responseDto;

import lombok.*;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountInfo {
    private String  accountName;
    private BigDecimal accountBalance;
    private  String accountNUmber;
}

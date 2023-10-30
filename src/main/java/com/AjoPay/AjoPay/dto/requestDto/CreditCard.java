package com.AjoPay.AjoPay.dto.requestDto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreditCard {
    private BigDecimal cardNUmber;
    private int CVV ;
    private int expireDate;

}

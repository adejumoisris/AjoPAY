package com.AjoPay.AjoPay.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "card")
public class CardDetails {
    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long Id;
    private BigDecimal cardNUmber;
    private int CVV ;
    private int expireDate;
    @OneToOne
    private Users users;
}

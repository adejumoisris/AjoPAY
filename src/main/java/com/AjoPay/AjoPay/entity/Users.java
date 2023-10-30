package com.AjoPay.AjoPay.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "users")
public class Users {
    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long Id;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String phoneNumber;
    private String passWord;

    @OneToOne
    private CardDetails cardDetails;


}

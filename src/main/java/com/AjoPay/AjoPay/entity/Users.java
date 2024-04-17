package com.AjoPay.AjoPay.entity;

import com.AjoPay.AjoPay.enums.UserStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long Id;
    private String userName;
    @Column(name = "firstName", length = 100)
    private String firstName;
    @Column(name = "lastName", length = 100)
    private String lastName;
    @Column(name = "email", length = 100)
    private String email;
    @Column(name = "phoneNumber", length = 100)
    private String phoneNumber;
    @Column(name = "passWord", length = 100)
    private String passWord;
    private String saltPassWord;
    @Builder.Default

    private Boolean isVerified = false;
    private String accountNumber;
    private BigDecimal accountBalance;

    @OneToOne(mappedBy = "users")
    private VerificationToken  verificationToken;

    private int counter;

    private String otp;
    private Long otpCreatedAt;
    @Enumerated(EnumType.STRING)
    private UserStatus status;

    @OneToOne
    private CardDetails cardDetails;

    @CreationTimestamp
    private LocalDate createdAt;
    @UpdateTimestamp
    private LocalDate updateAt;


}

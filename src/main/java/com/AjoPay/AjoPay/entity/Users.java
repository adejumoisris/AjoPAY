package com.AjoPay.AjoPay.entity;

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
    private String accountNumber;
    private BigDecimal AccountBalance;

    @OneToOne
    private CardDetails cardDetails;

    @CreationTimestamp
    private LocalDate createdAt;
    @UpdateTimestamp
    private LocalDate updateAt;


}

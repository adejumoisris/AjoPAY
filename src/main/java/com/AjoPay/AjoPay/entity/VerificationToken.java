package com.AjoPay.AjoPay.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "verification_token")
public class VerificationToken {

    private static final int EXPIRATION = 60 * 40;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String token;
    private Boolean isUsed = false;


    @OneToOne(targetEntity = Users.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    private Users users;


    private Date expiration;

    private Date calculateExpirationDate(int expireTimeInMinutes){
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Timestamp(cal.getTime().getTime()));
        cal.add(Calendar.MINUTE, expireTimeInMinutes);
        return new Date(cal.getTime().getTime());
    }
}

package com.AjoPay.AjoPay.repository;

import com.AjoPay.AjoPay.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {

    // check if user already exist in database ;
    Boolean existsByEmail(String email);
    Optional<Users> findByUserName(String username);

    Boolean existsByAccountNumber(String accountNumber);
    // find by account Number
    Users findByAccountNumber (String accountNumber);
    // finding information by email

     Optional<Users> findByEmail(String email);

//     Users findByVerificationToken(String verificationToken);
}

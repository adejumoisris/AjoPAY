package com.AjoPay.AjoPay.repository;

import com.AjoPay.AjoPay.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {

    // check if user already exist in database ;
    Boolean existsByEmail(String email);

    Boolean existsByAccountNumber(String accountNumber);
    // find by account Number
    Users findByAccountNumber (String accountNumber);
}

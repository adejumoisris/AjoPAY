package com.AjoPay.AjoPay.service.serviceImplementations;

import com.AjoPay.AjoPay.entity.Users;
import com.AjoPay.AjoPay.exception.CustomException;
import com.AjoPay.AjoPay.repository.UsersRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl  {
    private final UsersRepository usersRepository;

}

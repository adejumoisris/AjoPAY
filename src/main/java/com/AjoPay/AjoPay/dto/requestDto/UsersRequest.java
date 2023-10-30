package com.AjoPay.AjoPay.dto.requestDto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsersRequest {

    private String firstName;
    private String lastName;
    private String emailAddress;
    private String phoneNumber;
    private String passWord;

}

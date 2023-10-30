package com.AjoPay.AjoPay.dto.responseDto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsersResponse {
    private String firstName;
    private String emailAddress;
}

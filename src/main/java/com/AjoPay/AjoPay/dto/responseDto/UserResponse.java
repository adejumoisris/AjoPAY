package com.AjoPay.AjoPay.dto.responseDto;

import com.AjoPay.AjoPay.enums.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private String firstName;
    private String email;
    private UserStatus status;
}

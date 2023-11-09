package com.AjoPay.AjoPay.dto.requestDto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsersRequest {

    @NotBlank(message = "first name can not be empty")
    @Pattern(regexp =  "[a-zA-Z]*", message = " first name can only have letters ")
    @Size(min = 2, max = 100, message = "first name character length cannot be less than 2 and more than 100")
    @NotNull
    private String firstName;


    @NotBlank(message = "Lastname cannot be empty")
    @jakarta.validation.constraints.Pattern(regexp = "[a-zA-Z]*", message = "lastName can only have letters")
    @jakarta.validation.constraints.Size(message = "Lastname character length cannot be less than 2 and more than 100", min = 2, max = 100)
    private String lastName;

    @NotBlank(message = "email cannot be empty")
    @Email(message = "Must be a valid email!")
    private String email;



    @NotBlank(message = "phoneNumber cannot be empty")
    @jakarta.validation.constraints.Size(message = "Phone number character length cannot be less than 10 and more than 14", min = 10, max = 16)
    private String phoneNumber;


    @NotBlank(message = "phoneWord cannot be empty")
    @jakarta.validation.constraints.Size(message = "Pass Word  character length cannot be less than 2 and more than 14", min = 2, max = 16)
    private String passWord;

}

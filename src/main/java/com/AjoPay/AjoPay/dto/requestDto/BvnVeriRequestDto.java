package com.AjoPay.AjoPay.dto.requestDto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BvnVeriRequestDto {
    private String NIN;
    private String firtstName;
    private String redirectUrl;

}

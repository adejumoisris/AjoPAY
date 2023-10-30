package com.AjoPay.AjoPay.service.serviceImplementations;

import com.AjoPay.AjoPay.dto.requestDto.BvnVeriRequestDto;
import com.AjoPay.AjoPay.entity.AppVerificationEntity;
import com.AjoPay.AjoPay.repository.AppVerificationRepository;
import com.AjoPay.AjoPay.service.serviceInterface.AppVerificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AppVerificationServiceImpl implements AppVerificationService {

    private final AppVerificationRepository appVerificationRepository;

    // this method is for saving the app verification information gotten from users into the database
    @Override
    public void saveAppVerification(BvnVeriRequestDto bvnVeriRequestDto) {
        AppVerificationEntity appVerification = AppVerificationEntity.builder()
                .build();
         AppVerificationEntity save = appVerificationRepository.save(appVerification);
        System.out.println(" verification save successfully");

    }
}

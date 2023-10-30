package com.AjoPay.AjoPay.service.serviceImplementations;

import com.AjoPay.AjoPay.dto.requestDto.BvnVeriRequestDto;
import com.AjoPay.AjoPay.dto.responseDto.BvnResponseDto;
import com.AjoPay.AjoPay.exception.CustomException;
import com.AjoPay.AjoPay.service.serviceInterface.BvnVerificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class BvnVerificationServiceImpl implements BvnVerificationService {
    private final WebClient.Builder webClientBuilder;
    private final RestTemplate restTemplate;
    @Value("${bvn.api.uri}")
    private String apiUrl;
    @Value("${bvn.api.key}")
    private String apiKey;
    // Make an HTTP request to the BVN verification API, passing the BVN and API key.
    @Override
    public BvnResponseDto veryBvn(BvnVeriRequestDto bvnVeriRequestDto) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + apiKey );
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<BvnVeriRequestDto> request = new HttpEntity<>(bvnVeriRequestDto, headers);

        ResponseEntity<BvnResponseDto> responseEntity = restTemplate.postForEntity(apiUrl, request , BvnResponseDto.class);

        if (responseEntity.getStatusCode().is2xxSuccessful()){
            // verification was successful
            return responseEntity.getBody();

        }else {
            // Parse and return the response.
            // Handle errors and exceptions as needed.

            // Handle non-successful response (e.g., 4xx or 5xx errors)
            // You can log the response or take appropriate actions.
            // Throw a custom exception or return an error response.
            // Handle errors according to your application's requirements.

            throw new CustomException("information is invalid ");


        }



    }
}

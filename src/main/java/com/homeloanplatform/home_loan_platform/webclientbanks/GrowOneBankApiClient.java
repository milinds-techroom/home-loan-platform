package com.homeloanplatform.home_loan_platform.webclientbanks;

import com.homeloanplatform.home_loan_platform.dto.bank.GrowOneEligibilityRequest;
import com.homeloanplatform.home_loan_platform.dto.bank.GrowOneEligibilityResponse;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class GrowOneBankApiClient {
    private final WebClient webClient;

    public GrowOneBankApiClient(WebClient webClient) {
        this.webClient = webClient;
    }

    public GrowOneEligibilityResponse checkEligibility(GrowOneEligibilityRequest request){
        return webClient.post()
                .uri("/check-eligibility")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(request)
                .retrieve()
                .bodyToMono(GrowOneEligibilityResponse.class)
                .block();

    }
}

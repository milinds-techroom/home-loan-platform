package com.homeloanplatform.home_loan_platform.controller;

import com.homeloanplatform.home_loan_platform.dto.bank.GrowOneEligibilityResponse;
import com.homeloanplatform.home_loan_platform.service.HomeLoanService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(HomeLoanController.class)
class HomeLoanControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private HomeLoanService homeLoanService;

    @Test
    void shouldReturnOfferForValidInquiry() throws Exception {
        GrowOneEligibilityResponse bankResponse =
                new GrowOneEligibilityResponse();

        bankResponse.setInquiryId("123");
        bankResponse.setBankReferenceId("GROWONE-123");
        bankResponse.setEligible(true);
        bankResponse.setApprovedAmount(new BigDecimal("4500000"));
        bankResponse.setInterestRate(new BigDecimal("8.50"));
        bankResponse.setTenureYears(20);
        bankResponse.setProcessingFee(new BigDecimal("5000"));
        bankResponse.setBankName("GrowOne Bank");

        given(homeLoanService.processInquiry(any()))
                .willReturn(bankResponse);

        mockMvc.perform(post("/api/v1/home-loan/inquiry")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "monthlyIncome": 80000,
                                  "employmentType": "SALARIED",
                                  "propertyValue": 6000000,
                                  "loanRequired": 4500000,
                                  "tenureYears": 20,
                                  "propertyLocation": "Mumbai",
                                  "consentGiven": true
                                }
                                """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.eligible").value(true))
                .andExpect(jsonPath("$.bankName").value("GrowOne Bank"))
                .andExpect(jsonPath("$.interestRate").value(8.50));
    }

    @Test
    void shouldReturnBadRequestForInvalidInquiry() throws Exception {
        mockMvc.perform(post("/api/v1/home-loan/inquiry")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "monthlyIncome": 0,
                                  "employmentType": "",
                                  "propertyValue": 0,
                                  "loanRequired": -4500000,
                                  "tenureYears": 35,
                                  "propertyLocation": "",
                                  "consentGiven": false
                                }
                                """))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").value("Validation failed"))
                .andExpect(
                        jsonPath("$.validationErrors.monthlyIncome").exists()
                );
    }
}
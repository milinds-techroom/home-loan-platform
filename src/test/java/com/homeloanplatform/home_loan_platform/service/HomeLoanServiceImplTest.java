package com.homeloanplatform.home_loan_platform.service;

import com.homeloanplatform.home_loan_platform.dto.LoanInquiryRequest;
import com.homeloanplatform.home_loan_platform.dto.bank.GrowOneEligibilityRequest;
import com.homeloanplatform.home_loan_platform.dto.bank.GrowOneEligibilityResponse;
import com.homeloanplatform.home_loan_platform.webclientbanks.GrowOneBankApiClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class HomeLoanServiceImplTest {

    @Mock
    private GrowOneBankApiClient growOneBankApiClient;

    private HomeLoanServiceImpl homeLoanService;

    @BeforeEach
    void setUp() {
        homeLoanService = new HomeLoanServiceImpl(growOneBankApiClient);
    }

    /**
     * Verifies that the service maps a customer inquiry correctly
     * and returns the offer received from GrowOne Bank.
     */
    @Test
    void shouldProcessInquiryAndReturnGrowOneBankResponse() {
        LoanInquiryRequest inquiryRequest = new LoanInquiryRequest();

        inquiryRequest.setInquiryId("INQ-123");
        inquiryRequest.setMonthlyIncome(new BigDecimal("80000"));
        inquiryRequest.setEmploymentType("SALARIED");
        inquiryRequest.setPropertyValue(new BigDecimal("6000000"));
        inquiryRequest.setLoanRequired(new BigDecimal("4500000"));
        inquiryRequest.setTenureYears(20);
        inquiryRequest.setPropertyLocation("Mumbai");

        GrowOneEligibilityResponse bankResponse =
                new GrowOneEligibilityResponse();

        bankResponse.setInquiryId("INQ-123");
        bankResponse.setBankReferenceId("GROWONE-123");
        bankResponse.setEligible(true);
        bankResponse.setApprovedAmount(new BigDecimal("4500000"));
        bankResponse.setInterestRate(new BigDecimal("8.50"));
        bankResponse.setTenureYears(20);
        bankResponse.setProcessingFee(new BigDecimal("5000"));
        bankResponse.setBankName("GrowOne Bank");

        when(growOneBankApiClient.checkEligibility(any()))
                .thenReturn(bankResponse);

        GrowOneEligibilityResponse result =
                homeLoanService.processInquiry(inquiryRequest);

        assertSame(bankResponse, result);

        ArgumentCaptor<GrowOneEligibilityRequest> requestCaptor =
                ArgumentCaptor.forClass(GrowOneEligibilityRequest.class);

        verify(growOneBankApiClient)
                .checkEligibility(requestCaptor.capture());

        GrowOneEligibilityRequest sentToBank =
                requestCaptor.getValue();

        assertEquals("INQ-123", sentToBank.getInquiryId());
        assertEquals(
                new BigDecimal("80000"),
                sentToBank.getMonthlyIncome()
        );
        assertEquals("SALARIED", sentToBank.getEmploymentType());
        assertEquals(
                new BigDecimal("4500000"),
                sentToBank.getLoanRequired()
        );
        assertEquals("Mumbai", sentToBank.getPropertyLocation());
    }
}
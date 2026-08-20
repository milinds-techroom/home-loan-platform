package com.homeloanplatform.home_loan_platform.service;

import com.homeloanplatform.home_loan_platform.dto.LoanInquiryRequest;
import com.homeloanplatform.home_loan_platform.dto.bank.GrowOneEligibilityRequest;
import com.homeloanplatform.home_loan_platform.dto.bank.GrowOneEligibilityResponse;
import com.homeloanplatform.home_loan_platform.webclientbanks.GrowOneBankApiClient;
import org.springframework.stereotype.Service;

@Service
public class HomeLoanServiceImpl implements  HomeLoanService{
    private final GrowOneBankApiClient growOneBankApiClient;



    public HomeLoanServiceImpl(GrowOneBankApiClient growOneBankApiClient) {
        this.growOneBankApiClient = growOneBankApiClient;
    }

    @Override
    public GrowOneEligibilityResponse processInquiry(LoanInquiryRequest request) {

        GrowOneEligibilityRequest bankrequest = new GrowOneEligibilityRequest(
                request.getInquiryId(),
                request.getMonthlyIncome(),
                request.getEmploymentType(),
                request.getPropertyValue(),
                request.getLoanRequired(),
                request.getTenureYears(),
                request.getPropertyLocation()
        );

        GrowOneEligibilityResponse bankresponse = growOneBankApiClient.checkEligibility(bankrequest);

        return bankresponse;
    }
}

package com.homeloanplatform.home_loan_platform.service;

import com.homeloanplatform.home_loan_platform.dto.LoanInquiryRequest;
import com.homeloanplatform.home_loan_platform.dto.bank.GrowOneEligibilityResponse;


public interface HomeLoanService {
    public GrowOneEligibilityResponse processInquiry(LoanInquiryRequest request);
}

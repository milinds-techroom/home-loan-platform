package com.homeloanplatform.home_loan_platform.service;

import com.homeloanplatform.home_loan_platform.dto.LoanInquiryRequest;


public interface HomeLoanService {
    public  LoanInquiryRequest processInquiry(LoanInquiryRequest request);
}

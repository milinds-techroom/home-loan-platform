package com.homeloanplatform.home_loan_platform.service;

import com.homeloanplatform.home_loan_platform.dto.LoanInquiryRequest;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class HomeLoanServiceImpl implements  HomeLoanService{
    @Override
    public LoanInquiryRequest processInquiry(LoanInquiryRequest request) {
        return new LoanInquiryRequest(
               request.getMonthlyIncome(),
                request.getEmploymentType(),
                request.getPropertyValue(),
                request.getLoanRequired(),
                request.getTenureYears(),
                request.getPropertyLocation(),
                request.getConsentGiven()
        );
    }
}

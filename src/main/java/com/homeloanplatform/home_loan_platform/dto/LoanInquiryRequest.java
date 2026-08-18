package com.homeloanplatform.home_loan_platform.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class LoanInquiryRequest {
    private BigDecimal monthlyIncome;
    private String employmentType;
    private BigDecimal propertyValue;
    private BigDecimal loanRequired;
    private Integer tenureYears;
    private String propertyLocation;
    private Boolean consentGiven;
}

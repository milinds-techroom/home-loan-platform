package com.homeloanplatform.home_loan_platform.dto.bank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GrowOneEligibilityResponse {
    private String inquiryId;
    private String bankReferenceId;
    private boolean eligible;
    private BigDecimal approvedAmount;
    private BigDecimal interestRate;
    private Integer tenureYears;
    private BigDecimal processingFee;
    private String bankName;

}

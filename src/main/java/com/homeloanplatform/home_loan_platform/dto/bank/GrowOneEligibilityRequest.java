package com.homeloanplatform.home_loan_platform.dto.bank;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GrowOneEligibilityRequest {
    private String inquiryId;
    private BigDecimal monthlyIncome;
    private String employmentType;
    private BigDecimal propertyValue;
    private BigDecimal loanRequired;
    private Integer tenureYears;
    private String propertyLocation;


}

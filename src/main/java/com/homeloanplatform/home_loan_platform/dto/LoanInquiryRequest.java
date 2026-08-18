package com.homeloanplatform.home_loan_platform.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoanInquiryRequest {
    @NotNull
    @Positive
    private BigDecimal monthlyIncome;

    @NotBlank
    private String employmentType;

    @NotNull
    @Positive
    private BigDecimal propertyValue;

    @NotNull
    @Positive
    private BigDecimal loanRequired;

    @NotNull
    @Min(1)
    @Max(30)
    private Integer tenureYears;

    @NotBlank
    private String propertyLocation;

    @NotNull
    @AssertTrue
    private Boolean consentGiven;


}


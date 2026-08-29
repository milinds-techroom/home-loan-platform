package com.homeloanplatform.home_loan_platform.controller;

import com.homeloanplatform.home_loan_platform.dto.LoanInquiryRequest;
import com.homeloanplatform.home_loan_platform.dto.bank.GrowOneEligibilityResponse;
import com.homeloanplatform.home_loan_platform.service.HomeLoanService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/home-loan")
public class HomeLoanController {

    private  final HomeLoanService service;

    public HomeLoanController(HomeLoanService service) {
        this.service = service;
    }

    @PostMapping("/inquiry")
    public ResponseEntity<?> processInquiry(@Valid @RequestBody LoanInquiryRequest loanInquiryRequest){
        System.out.println("call in home loan service");
        long id=(long) (Math.random() * 1000) + 1;
        loanInquiryRequest.setInquiryId(Long.toString(id));
        GrowOneEligibilityResponse response = this.service.processInquiry(loanInquiryRequest);
        return ResponseEntity.ok(response);
    }
}

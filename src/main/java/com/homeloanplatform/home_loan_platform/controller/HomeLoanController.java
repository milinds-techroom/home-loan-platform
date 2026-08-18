package com.homeloanplatform.home_loan_platform.controller;

import com.homeloanplatform.home_loan_platform.dto.LoanInquiryRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/v1/home-loan")
public class HomeLoanController {

    @PostMapping("/inquiry")
    public ResponseEntity<?> inquiry(@RequestBody LoanInquiryRequest loanInquiryRequest){
        System.out.println(loanInquiryRequest.getPropertyValue());
        return ResponseEntity.ok(loanInquiryRequest);
    }
}

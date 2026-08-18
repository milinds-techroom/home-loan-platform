package com.homeloanplatform.home_loan_platform.controller;

import com.homeloanplatform.home_loan_platform.dto.LoanInquiryRequest;
import com.homeloanplatform.home_loan_platform.service.HomeLoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/v1/home-loan")
public class HomeLoanController {

    private  final HomeLoanService service;

    public HomeLoanController(HomeLoanService service) {
        this.service = service;
    }

    @PostMapping("/inquiry")
    public ResponseEntity<?> processInquiry(@RequestBody LoanInquiryRequest loanInquiryRequest){
        System.out.println(loanInquiryRequest.getPropertyValue());
        LoanInquiryRequest response = this.service.processInquiry(loanInquiryRequest);
        return ResponseEntity.ok(response);
    }
}

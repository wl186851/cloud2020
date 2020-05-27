package com.wu.springcloud.service;

import org.springframework.stereotype.Component;

@Component
public class PaymentFallbackService implements PaymentHystrixService{
    public String paymentInfo_ok(Integer id) {
        return "---------------PaymentFallbackService------paymentInfo_ok";
    }

    public String paymentInfo_timeout(Integer id) {
        return "PaymentFallbackService----------paymentInfo_timeout";
    }
}

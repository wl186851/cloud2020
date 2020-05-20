package com.wu.springcloud.service;

import com.wu.springcloud.entities.CommonResult;
import com.wu.springcloud.entities.Payment;
import feign.Param;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(value = "CLOUD-PAYMENT-SERVICE")
public interface PaymentFeignService {

   @GetMapping(value = "/payment/get/{id}")
   public CommonResult<Payment> getPaymentById(@PathVariable("id") Integer id);

   @GetMapping(value = "/payment/feign/timeout")
   public String paymentFeignTimeout();
}

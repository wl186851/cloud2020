package com.wu.springcloud.controller;

import com.alibaba.druid.sql.parser.Lexer;
import com.wu.springcloud.entities.CommonResult;
import com.wu.springcloud.entities.Payment;
import com.wu.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping(value = "/payment/create")
    public CommonResult create (Payment payment){
       int result= this.paymentService.create(payment);
        log.info("************************插入结果："+result);

       if(result > 0){
           return new CommonResult(200,"success",result);
       }else {
           return new CommonResult(500,"failed",null);
       }
    }

@GetMapping(value = "/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id")Integer id){
        Payment payment = this.paymentService.getPaymentById(id);
        log.info("************************获取结果："+payment);

        if(payment != null){
            return new CommonResult(200,"success",payment);
        }else {
            return new CommonResult(500,"failed",null);
        }
    }
}

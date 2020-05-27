package com.wu.springcloud.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.wu.springcloud.service.PaymentHystrixService;
import javafx.beans.DefaultProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
@DefaultProperties(defaultFallback = "paymentInfo_Global_FallbackMethod")
public class OrderHystrixController {

    @Autowired
    private PaymentHystrixService paymentHystrixService;

    @GetMapping(value = "/consumer/payment/hytrix/ok/{id}")
    public String paymentInfo_ok(@PathVariable("id") Integer id){
        String result = paymentHystrixService.paymentInfo_ok(id);
        return result;
    }

    @GetMapping(value = "/consumer/payment/hystrix/timeout/{id}")
//    @HystrixCommand(fallbackMethod = "paymentInfo_TimeOutHandler",commandProperties = {
//            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "5000")
//    })
    @HystrixCommand
    public String paymentInfo_timeout(Integer id){
        int timenum = 3;
        try {
            TimeUnit.SECONDS.sleep(timenum);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        return "线程池："+Thread.currentThread().getName()+"paymentInfo_ok, id:"+id+"\t"+"耗时"+timenum+"秒钟";
    }

    public String paymentInfo_TimeOutHandler(Integer id){
        return "线程池："+Thread.currentThread().getName()+"paymentInfo_TimeOutHandler, id:"+id+"\t"+"failed";
    }

    public String paymentInfo_Global_FallbackMethod(){
        return "Clobal异常处理信息，请稍后再试！！！！！！";
    }
}

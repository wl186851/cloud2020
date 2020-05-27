package com.wu.springcloud.controller;

import com.alibaba.druid.sql.parser.Lexer;
import com.wu.springcloud.entities.CommonResult;
import com.wu.springcloud.entities.Payment;
import com.wu.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class PaymentController {

    @Value("${server.port}")
    private String serverport;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private PaymentService paymentService;

    @PostMapping(value = "/payment/create")
    public CommonResult create (@RequestBody Payment payment){
       int result= this.paymentService.create(payment);
        log.info("************************插入结果是："+result);

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
            return new CommonResult(200,"success"+serverport,payment);
        }else {
            return new CommonResult(500,"failed",null);
        }
    }

    @GetMapping(value = "/payment/discovery")
    public Object discovery(){
        List<String> services = discoveryClient.getServices();
        for (String element:services){
            log.info("elemnt:"+element);
        }
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance instance:instances){
            log.info(instance.getServiceId()+"\t"+instance.getHost()+"\t"+instance.getPort()+"\t"+instance.getUri());
        }
        return this.discoveryClient;
    }

    @GetMapping(value = "/payment/lb")
    public String getPaymentLB(){
        return serverport;
    }

    @GetMapping(value = "/payment/feign/timeout")
    public String paymentFeignTimeout(){
        try {
            TimeUnit.SECONDS.sleep(3);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        return serverport;
    }


}

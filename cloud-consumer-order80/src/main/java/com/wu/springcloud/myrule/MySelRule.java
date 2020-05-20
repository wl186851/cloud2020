package com.wu.springcloud.myrule;

import com.netflix.loadbalancer.IRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MySelRule{

    @Bean
    public IRule myRule(){
        return new RandomRule_WL();//达到的目的，用我们重新选择的随机算法代替轮替
    }
}

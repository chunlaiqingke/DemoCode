package com.handsome.spring.config;

import com.handsome.spring.dao.Dao1;
import com.handsome.spring.dao.Dao2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean("dao1")
    public Dao1 dao1(){
        return new Dao1();
    }

    @Bean("dao1")
    public Dao2 dao2(){
        return new Dao2();
    }
}

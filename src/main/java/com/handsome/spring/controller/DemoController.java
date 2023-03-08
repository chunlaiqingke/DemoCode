package com.handsome.spring.controller;

import com.handsome.spring.dao.Dao1;
import com.handsome.spring.dao.Dao2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/")
public class DemoController {

    @Resource
    Dao2 dao1;

    @RequestMapping("demo")
    public String demo(){
        return "Hello world" + dao1.dao2();
    }
}

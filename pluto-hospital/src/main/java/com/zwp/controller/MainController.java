package com.zwp.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("hos/main")
public class MainController {

    @Value("${student.name}")
    private String userName;

    @PostMapping("test")
    public String test(){
        System.out.println("请求成功");
        return userName;
    }
}

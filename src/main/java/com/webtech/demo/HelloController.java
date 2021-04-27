package com.webtech.demo;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class HelloController {

    @RequestMapping("/")
    public String landing() { return "Hello World!"; }

    @RequestMapping("/login")
    public String login() {
        return "Login Page";
    }

}
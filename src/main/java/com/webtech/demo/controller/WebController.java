package com.webtech.demo.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@RestController
public class WebController {

    // Create a Logger
    Logger logger = Logger.getLogger(UrlsController.class.getName());

    @GetMapping("/")
    public String testPage(Model model) {
        //model.addAttribute("test", "test123");
        return "index";
    }

    @RequestMapping("/login")
    public String login() {
        return "login page";
    }

    @RequestMapping("/unvalid")
    public String unvalidInfo() {
        return "unvalid url";
    }

}

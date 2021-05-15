package com.webtech.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

    @GetMapping ("/")
    public String testPage(Model model) {
        //model.addAttribute("test", "test123");
        return "index";
    }

}

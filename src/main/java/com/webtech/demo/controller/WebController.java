package com.webtech.demo.controller;

import com.webtech.demo.config.Endpoints;
import com.webtech.demo.config.ViewNames;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class WebController {

    @GetMapping(Endpoints.INDEX)
    public ModelAndView showIndexPage() { return new ModelAndView(ViewNames.INDEX); }

    @GetMapping(path = Endpoints.REGISTER)
    public ModelAndView showRegisterPage() { return new ModelAndView(ViewNames.REGISTER); }

    @GetMapping(path = Endpoints.LOGIN)
    public ModelAndView showLoginPage() {return new ModelAndView(ViewNames.LOGIN); }

    @GetMapping(Endpoints.INVALID)
    public ModelAndView invalidInfo() {return new ModelAndView(ViewNames.INVALID); }
  
}

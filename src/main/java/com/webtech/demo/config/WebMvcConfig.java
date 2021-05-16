package com.webtech.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    // not used atm
    public void addViewController(ViewControllerRegistry registry) {
        //registry.addViewController(Endpoints.INDEX).setViewName(ViewNames.INDEX);
        //registry.addViewController(Endpoints.LOGIN).setViewName(ViewNames.LOGIN);
        //registry.addViewController(Endpoints.REGISTER).setViewName(ViewNames.REGISTER);
        //registry.addViewController(Endpoints.INVALID).setViewName(ViewNames.INVALID);
    }

}

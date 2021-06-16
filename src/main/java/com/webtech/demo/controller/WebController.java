package com.webtech.demo.controller;

import com.webtech.demo.config.Endpoints;
import com.webtech.demo.config.ViewNames;
import com.webtech.demo.model.Url;
import com.webtech.demo.service.UrlService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class WebController {

    private final UrlService urlService;

    public WebController(UrlService urlService) {
        this.urlService = urlService;
    }

    @GetMapping(path = Endpoints.INDEX)
    public ModelAndView showIndexPage() { return new ModelAndView(ViewNames.INDEX); }


    @PostMapping(path = Endpoints.INDEX)
    public RedirectView handleNewUrlRequest(Url longUrl){

        if (urlService.validProvidedUrl(longUrl)) {
            urlService.generateUrl(longUrl);
            return new RedirectView(ViewNames.INDEX);
        }
        else {
            return new RedirectView(ViewNames.INVALID);
        }

    }

    @GetMapping(path = Endpoints.MANAGE)
    public ModelAndView showRegisterPage() { return new ModelAndView(ViewNames.MANAGE); }


    @GetMapping(path = Endpoints.INVALID)
    public ModelAndView invalidInfo() { return new ModelAndView(ViewNames.INVALID); }

}

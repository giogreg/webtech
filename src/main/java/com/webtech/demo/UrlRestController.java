package com.webtech.demo;

import com.webtech.demo.model.Url;
import com.webtech.demo.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.logging.Logger;

@RestController
public class UrlRestController {

    // Create a Logger
    Logger logger = Logger.getLogger(UrlRestController.class.getName());

    @Autowired
    private UrlService urlService;

    @RequestMapping("/")
    public String landing() { return "Landing Page"; }

    @RequestMapping("/login")
    public String login() {
        return "Login Page";
    }

    @PostMapping(value="/generate", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Url> generateShortLink(@RequestBody Url longUrl){
        try{
            Url resUrl = urlService.generateUrl(longUrl);
            logger.info(resUrl.toString());
            return new ResponseEntity<Url>(resUrl, HttpStatus.CREATED);
        }
        catch(NullPointerException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{shortUrl}")
    @ResponseStatus(HttpStatus.OK)
    public RedirectView redirectToLongUrl(@PathVariable String shortUrl){
        Url resUrl = urlService.findByShortUrl(shortUrl);
        logger.info(resUrl.toString());
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl(resUrl.getLongUrl());
        return redirectView;
    }

    @GetMapping("/unvalid/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Url> unvalid(@PathVariable long id){
        Url resUrl = urlService.setGueltigBis(id);
        return new ResponseEntity<Url>(resUrl, HttpStatus.CREATED);
    }
}
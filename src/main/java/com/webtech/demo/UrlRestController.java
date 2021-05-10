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
    public String landing() { return "landing page"; }

    @RequestMapping("/login")
    public String login() {
        return "login page";
    }

    @RequestMapping("/unvalid")
    public String unvalidInfo() {
        return "unvalid url";
    }

    @PostMapping(value="/generate", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Url> generateShortLink(@RequestBody Url longUrl){
        if (urlService.validProvidedUrl(longUrl)) {
            Url resUrl = urlService.generateUrl(longUrl);
            logger.info(resUrl.toString());
            return new ResponseEntity<Url>(resUrl, HttpStatus.CREATED);
        }
        else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value="/generateexpired", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Url> generateUrl30daysValid(@RequestBody Url longUrl){
    if (urlService.validProvidedUrl(longUrl)) {
            Url resUrl = urlService.generateUrl30daysValid(longUrl);
            logger.info(resUrl.toString());
            return new ResponseEntity<Url>(resUrl, HttpStatus.CREATED);
        }
        else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{shortUrl}")
    public RedirectView redirectToLongUrl(@PathVariable String shortUrl){
        Url resUrl = urlService.findByShortUrl(shortUrl);
        RedirectView redirectView = new RedirectView();
        if (resUrl == null){
            redirectView.setUrl("/unvalid");
            return redirectView;
        }
        if (urlService.isUrlValid(resUrl)) {
            logger.info(resUrl.toString());
            redirectView.setUrl(resUrl.getLongUrl());
            return redirectView;
        }
        else {
            redirectView.setUrl("/unvalid");
            return redirectView;
        }
    }

    @GetMapping("/unvalid/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Url> unvalid(@PathVariable long id){
        try {
            Url resUrl = urlService.setGueltigBis(id);
            logger.info("unvalid: " + resUrl.toString());
            return new ResponseEntity<Url>(resUrl, HttpStatus.CREATED);
        }
        catch (NullPointerException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
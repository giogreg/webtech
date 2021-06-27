package com.webtech.demo.controller;

import com.webtech.demo.config.Endpoints;
import com.webtech.demo.model.Url;
import com.webtech.demo.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;
import java.util.ArrayList;
import java.util.logging.Logger;

@RestController
public class UrlsController{

    // Create a Logger
    Logger logger = Logger.getLogger(UrlsController.class.getName());

    @Autowired
    private UrlService urlService;

    @PostMapping(value="/urls", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Url> generateShortLink(@RequestBody Url longUrl){
        if (urlService.validProvidedUrl(longUrl)) {
            Url resUrl = urlService.generateUrl(longUrl);
            logger.info(resUrl.toString());
            return new ResponseEntity<>(resUrl, HttpStatus.CREATED);
        }
        else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value="/eurls", consumes = MediaType.APPLICATION_JSON_VALUE)
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

    @GetMapping(value="/urls/{userHash}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Url>> invalid(@PathVariable String userHash){
        List<Url> urlList = urlService.findAllByUserHash(userHash);
        logger.info("fetch urlList: " + userHash);
        return new ResponseEntity<>(urlList, HttpStatus.CREATED);
    }

    @GetMapping("/{shortUrl}")
    public RedirectView redirectToLongUrl(@PathVariable String shortUrl){
        Url resUrl = urlService.findByShortUrl(shortUrl);
        RedirectView redirectView = new RedirectView();
        if (resUrl == null){
            redirectView.setUrl(Endpoints.INVALID);
            return redirectView;
        }
        if (urlService.isUrlValid(resUrl)) {
            logger.info(resUrl.toString());
            redirectView.setUrl(resUrl.getLongUrl());
            return redirectView;
        }
        else {
            redirectView.setUrl(Endpoints.INVALID);
            return redirectView;
        }
    }

    @DeleteMapping("/urls/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Url> invalid(@PathVariable long id){
        try {
            Url resUrl = urlService.deleteUrl(id);
            logger.info("deleted: " + resUrl.toString());
            return new ResponseEntity<>(resUrl, HttpStatus.CREATED);
        }
        catch (NullPointerException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
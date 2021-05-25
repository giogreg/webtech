package com.webtech.demo.service;

import com.webtech.demo.model.Url;
import com.webtech.demo.repository.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.thymeleaf.util.StringUtils;

import java.time.LocalDateTime;
import java.util.Random;

@Component
public class UrlService {

    @Autowired
    private UrlRepository urlRepository;

    public Url generateUrl(Url longUrl) {
        if (!StringUtils.isEmpty(longUrl.getLongUrl())){
            longUrl.setShortUrl(shortenUrl());
            longUrl.setGueltigVon(LocalDateTime.now());
            longUrl.setGueltigBis(LocalDateTime.now().plusYears(9999));
            urlRepository.save(longUrl);
            return longUrl;
        }
        return null;
    }

    public Url generateUrl30daysValid(Url longUrl) {
        if (!StringUtils.isEmpty(longUrl.getLongUrl())){
            String shortUrl = shortenUrl();
            longUrl.setShortUrl(shortUrl);
            longUrl.setGueltigVon(LocalDateTime.now());
            longUrl.setGueltigBis(LocalDateTime.now().plusDays(30));
            urlRepository.save(longUrl);
            return longUrl;
        }
        return null;
    }

    private String shortenUrl() {
        Random r = new Random();
        String shortUrl = "";
        System.out.println();
        do{
        String alphabet = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        for (int i = 0; i < 6; i++) {
            shortUrl += alphabet.charAt(r.nextInt(alphabet.length()));
            }
        } while (urlRepository.findByShortUrl(shortUrl) != null);
        return shortUrl;
    }

    public Url findByShortUrl(String shortUrl){
        Url url = urlRepository.findByShortUrl(shortUrl);
        return url;
    }

    public Url getLastEntry(){
        int id = (int )urlRepository.count();
        Url lastEntry = urlRepository.findById(id);
        return lastEntry;
    }

    public Url setGueltigBis(long id){
        Url url = urlRepository.findById(id);
        url.setGueltigBis(LocalDateTime.now());
        urlRepository.save(url);
        return url;
    }

    public boolean isUrlValid(Url url){
        if (LocalDateTime.now().isAfter(url.getGueltigBis())) {
            return false;
        }
        else {
            return true;
        }
    }

    public boolean validProvidedUrl(Url url){
        if (!(url.getLongUrl().contains("https://")) || url.getLongUrl().contains("http://")){
            url.setlongUrl("https://" + url.getLongUrl());
        }
        if (url.getLongUrl().contains(".")){
            return true;
        }
        else {
            return false;
        }
    }
}

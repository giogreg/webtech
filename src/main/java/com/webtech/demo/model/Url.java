package com.webtech.demo.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Url {

    @Id @GeneratedValue(strategy=GenerationType.SEQUENCE)
    private long id;
    @Lob
    private String longUrl;
    private String shortUrl;
    private LocalDateTime gueltigVon;
    private LocalDateTime gueltigBis; //optional
    private String userHash;

    public Url(long id, String longUrl, String shortUrl, LocalDateTime gueltigVon, LocalDateTime gueltigBis, String userHash) {
        this.id = id;
        this.longUrl = longUrl;
        this.shortUrl = shortUrl;
        this.gueltigVon = gueltigVon;
        this.gueltigBis = gueltigBis;
        this.userHash = userHash;
    }

    public Url(){
    }

    public void setlongUrl(String longUrl) {
        this.longUrl = longUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    public void setGueltigVon(LocalDateTime gueltigVon) {
        this.gueltigVon = gueltigVon;
    }

    public void setGueltigBis(LocalDateTime gueltigBis) {
        this.gueltigBis = gueltigBis;
    }

    public void setUserHash(String userHash) {
        this.userHash = userHash;
    }

    public long getId() {
        return id;
    }

    public String getLongUrl() {
        return longUrl;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public LocalDateTime getGueltigVon() {
        return gueltigVon;
    }

    public LocalDateTime getGueltigBis() {
        return gueltigBis;
    }

    public String getUserHash() {
        return userHash;
    }

    @Override
    public String toString() {
        return "Url{" +
                "id=" + id +
                ", longUrl='" + longUrl + '\'' +
                ", shortUrl='" + shortUrl + '\'' +
                ", gueltigVon=" + gueltigVon +
                ", gueltigBis=" + gueltigBis +
                ", userHash=" + userHash +
                '}';
    }
}

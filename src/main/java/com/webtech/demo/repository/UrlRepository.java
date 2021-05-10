package com.webtech.demo.repository;

import com.webtech.demo.model.Url;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UrlRepository extends JpaRepository<Url, Long> {

    public Url findByShortUrl(String shortUrl);

    public Url findById(long id);

}

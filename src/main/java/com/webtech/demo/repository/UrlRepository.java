package com.webtech.demo.repository;

import com.webtech.demo.model.Url;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface UrlRepository extends JpaRepository<Url, Long> {

    @Transactional
    public Url findByShortUrl(String shortUrl);

    @Transactional
    public Url findById(long id);

    @Transactional
    public List<Url> findAllByUserHash(String userHash);

}

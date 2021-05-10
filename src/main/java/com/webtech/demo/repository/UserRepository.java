package com.webtech.demo.repository;

import com.webtech.demo.model.Url;
import com.webtech.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Url, Long> {

    public User findUserById(long id);

}

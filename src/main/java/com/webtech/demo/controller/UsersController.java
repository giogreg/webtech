package com.webtech.demo.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class UsersController {

    @GetMapping("/users")
    String helloUser(@AuthenticationPrincipal OidcUser user) {
        if (user == null) {
            return "no user";
        } else {
            return user.getAttribute("email");
        }
    }
}
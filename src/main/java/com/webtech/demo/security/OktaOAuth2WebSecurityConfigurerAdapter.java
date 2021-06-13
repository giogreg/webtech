package com.webtech.demo.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/*
If you want the user to only have access to a route if they are signed in, require authentication for just those routes.
 */
@Configuration
class OktaOAuth2WebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                // Require authentication for all requests under /api/private
                .antMatchers("/api/private/**").authenticated()
                // enable OAuth2/OIDC
                .and()
                .oauth2Login();
    }

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        // all routes protected
//        http.authorizeRequests()
//                .anyRequest().authenticated()
//                // enable OAuth2/OIDC
//                .and()
//                .oauth2Login();
//    }
}


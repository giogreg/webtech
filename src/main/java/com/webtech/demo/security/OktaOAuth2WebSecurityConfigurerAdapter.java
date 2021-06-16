package com.webtech.demo.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/*
require authentication for specific routes.
*/
@Configuration
class OktaOAuth2WebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                // Require authentication for all requests under /manage
                .antMatchers("/manage").authenticated()
                // After we logout, redirect to root page
                // by default Spring will send you to /login?logout
                .and().logout().logoutSuccessUrl("/")
                // enable OAuth2/OIDC
                .and()
                .oauth2Login();
    }
    //always require login for all paths
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


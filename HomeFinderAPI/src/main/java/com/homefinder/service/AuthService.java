package com.homefinder.service;


import com.google.auth.Credentials;
import com.google.firebase.auth.FirebaseAuth;
import com.homefinder.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

@Service
public class AuthService {
    final FirebaseAuth firebaseAuth;
    final HttpServletRequest httpServletRequest;
    final SecurityProperties securityProps;

    public AuthService(FirebaseAuth firebaseAuth, HttpServletRequest httpServletRequest, SecurityProperties securityProps) {
        this.firebaseAuth = firebaseAuth;
        this.httpServletRequest = httpServletRequest;
        this.securityProps = securityProps;
    }

    public User getUser(){
        User userPrincipal = null;
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Object principal = securityContext.getAuthentication().getPrincipal();
        if (principal instanceof User) {
            userPrincipal = ((User) principal);
        }
        return userPrincipal;
    }

    public Credentials getCredentials() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        return (Credentials) securityContext.getAuthentication().getCredentials();
    }

    public String getBearerToken(HttpServletRequest request) {
        String bearerToken = null;
        String authorization = request.getHeader("Authorization");
        if (StringUtils.hasText(authorization) && authorization.startsWith("Bearer ")) {
            bearerToken = authorization.substring(7);
        }
        return bearerToken;
    }

}

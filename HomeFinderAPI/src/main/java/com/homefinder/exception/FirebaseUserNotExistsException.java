package com.homefinder.exception;

import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;

public class FirebaseUserNotExistsException extends AuthenticationCredentialsNotFoundException {
    public FirebaseUserNotExistsException() {
        super("User Not Found");
    }
}

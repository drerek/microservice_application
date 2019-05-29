package com.kpi.project.first.service.exception.runtime;

public class AuthenticationException extends CustomRuntimeException {
    public AuthenticationException(String message) {
        super(message);
    }
}

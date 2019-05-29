package com.kpi.project.first.service.exception.runtime;

public class SecretKeyNotFoundException extends CustomRuntimeException {

    public SecretKeyNotFoundException(String message) {
        super(message);
    }

}

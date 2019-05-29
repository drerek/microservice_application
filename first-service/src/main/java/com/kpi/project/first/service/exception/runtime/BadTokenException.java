package com.kpi.project.first.service.exception.runtime;

import com.kpi.project.first.service.exception.runtime.CustomRuntimeException;

public class BadTokenException extends CustomRuntimeException {
    public BadTokenException(String message) {
        super(message);
    }
}

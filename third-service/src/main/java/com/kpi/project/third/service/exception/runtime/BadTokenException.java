package com.kpi.project.third.service.exception.runtime;

import com.kpi.project.third.service.exception.runtime.CustomRuntimeException;

public class BadTokenException extends CustomRuntimeException {
    public BadTokenException(String message) {
        super(message);
    }
}

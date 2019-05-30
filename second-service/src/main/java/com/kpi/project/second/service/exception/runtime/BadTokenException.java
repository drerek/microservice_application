package com.kpi.project.second.service.exception.runtime;

import com.kpi.project.second.service.exception.runtime.CustomRuntimeException;

public class BadTokenException extends CustomRuntimeException {
    public BadTokenException(String message) {
        super(message);
    }
}

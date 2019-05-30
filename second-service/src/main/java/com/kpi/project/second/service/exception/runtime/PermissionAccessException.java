package com.kpi.project.second.service.exception.runtime;

public class PermissionAccessException extends RuntimeException {
    public PermissionAccessException(String message) {
        super(message);
    }
}

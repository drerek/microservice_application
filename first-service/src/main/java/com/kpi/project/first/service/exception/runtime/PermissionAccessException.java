package com.kpi.project.first.service.exception.runtime;

public class PermissionAccessException extends RuntimeException {
    public PermissionAccessException(String message) {
        super(message);
    }
}

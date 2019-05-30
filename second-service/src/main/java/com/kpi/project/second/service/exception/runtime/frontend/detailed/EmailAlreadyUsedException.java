package com.kpi.project.second.service.exception.runtime.frontend.detailed;


public class EmailAlreadyUsedException extends FrontendDetailedException {
    public EmailAlreadyUsedException(String message) {
        super(message);
    }
}
package com.kpi.project.third.service.exception.runtime.frontend.detailed;


public class EmailAlreadyUsedException extends FrontendDetailedException {
    public EmailAlreadyUsedException(String message) {
        super(message);
    }
}
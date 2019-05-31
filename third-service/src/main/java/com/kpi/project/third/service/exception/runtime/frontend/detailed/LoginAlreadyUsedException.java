package com.kpi.project.third.service.exception.runtime.frontend.detailed;


public class LoginAlreadyUsedException  extends FrontendDetailedException{
    public LoginAlreadyUsedException(String message) {
        super(message);
    }
}
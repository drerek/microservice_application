package com.kpi.project.second.service.exception.runtime.frontend.detailed;


public class LoginAlreadyUsedException  extends FrontendDetailedException{
    public LoginAlreadyUsedException(String message) {
        super(message);
    }
}
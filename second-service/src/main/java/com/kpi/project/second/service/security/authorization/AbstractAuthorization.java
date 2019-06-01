package com.kpi.project.second.service.security.authorization;

import com.kpi.project.second.service.security.AuthenticationFacade;

public abstract class AbstractAuthorization {

    protected AuthenticationFacade authenticationFacade;

    public AbstractAuthorization(AuthenticationFacade authenticationFacade) {
        this.authenticationFacade = authenticationFacade;
    }

    public boolean isUserCorrect(String userLogin) {
        System.out.println("userlogin="+userLogin);
        System.out.println("auth_login="+authenticationFacade.getAuthentication().getLogin());
        return userLogin.equals(authenticationFacade.getAuthentication().getLogin());
    }
}

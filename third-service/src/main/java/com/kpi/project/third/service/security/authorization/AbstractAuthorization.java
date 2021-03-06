package com.kpi.project.third.service.security.authorization;

import com.kpi.project.third.service.security.AuthenticationFacade;

public abstract class AbstractAuthorization {

    protected AuthenticationFacade authenticationFacade;

    public AbstractAuthorization(AuthenticationFacade authenticationFacade) {
        this.authenticationFacade = authenticationFacade;
    }

    public boolean isUserCorrect(String userId) {
        return userId.equals(authenticationFacade.getAuthentication().getLogin());
    }
}

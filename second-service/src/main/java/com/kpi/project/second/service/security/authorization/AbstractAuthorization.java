package com.kpi.project.second.service.security.authorization;

import com.kpi.project.second.service.security.AuthenticationFacade;

public abstract class AbstractAuthorization {

    protected AuthenticationFacade authenticationFacade;

    public AbstractAuthorization(AuthenticationFacade authenticationFacade) {
        this.authenticationFacade = authenticationFacade;
    }

    public boolean isUserCorrect(int userId) {
        return userId == authenticationFacade.getAuthentication().getId();
    }
}

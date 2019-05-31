package com.kpi.project.third.service.security.authorization;

import com.kpi.project.third.service.security.AuthenticationFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProfileAuthorization extends AbstractAuthorization {

    @Autowired
    public ProfileAuthorization(AuthenticationFacade authenticationFacade) {
        super(authenticationFacade);
    }
}

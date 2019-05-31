package com.kpi.project.third.service.security.authorization;

import com.kpi.project.third.service.security.AuthenticationFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WishListAuthorization extends AbstractAuthorization {

    @Autowired
    public WishListAuthorization(AuthenticationFacade authenticationFacade) {
        super(authenticationFacade);
    }

}

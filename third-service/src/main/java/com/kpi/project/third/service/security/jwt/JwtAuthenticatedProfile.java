package com.kpi.project.third.service.security.jwt;

import com.kpi.project.third.service.config.SecurityConfig;
import com.kpi.project.third.service.entity.User;
import jdk.nashorn.internal.parser.Token;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.Collections;

public class JwtAuthenticatedProfile implements Authentication {
    private final User user;
    private final String token;

    public JwtAuthenticatedProfile(User user, String token) {
        this.user = user;
        this.token = token;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(SecurityConfig.ROLE_USER));
    }

    @Override
    public Object getCredentials() {
        return token;
    }

    @Override
    public Object getDetails() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return user;
    }

    @Override
    public boolean isAuthenticated() {
        return true;
    }

    @Override
    public void setAuthenticated(boolean b) throws IllegalArgumentException {

    }

    @Override
    public String getName() {
        return user.getLogin();
    }
}

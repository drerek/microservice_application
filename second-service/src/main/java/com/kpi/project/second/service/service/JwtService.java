package com.kpi.project.second.service.service;

import com.kpi.project.second.service.entity.User;
import com.kpi.project.second.service.security.jwt.SecretKeyProvider;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;


@PropertySource("classpath:image.properties")
@PropertySource("classpath:jwt.properties")
@Component
public class JwtService {

    private static Logger log = LoggerFactory.getLogger(JwtService.class);

    private final static String JWT_LOGIN = "jwt.login";

    @Autowired
    private Environment env;

    private final SecretKeyProvider secretKeyProvider;

    @Autowired
    public JwtService(SecretKeyProvider secretKeyProvider) {
        this.secretKeyProvider = secretKeyProvider;
    }

    public User verify(String token) {
        log.debug("Trying to get secret key form SecretKeyProvider");

        byte[] secretKey = secretKeyProvider.getKey();

        log.debug("Trying to parse email from token '{}'", token);

        Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
        String login = claims.getBody().get(env.getProperty(JWT_LOGIN)).toString();

        log.debug("Login '{}' was parsed successfully", login);
        User user = new User();
        user.setLogin(login);
        return user;
    }
}

package com.kpi.project.third.service.service;

import com.kpi.project.third.service.entity.User;
import com.kpi.project.third.service.security.jwt.SecretKeyProvider;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;

import static java.time.ZoneOffset.UTC;


@PropertySource("classpath:image.properties")
@PropertySource("classpath:jwt.properties")
@Component
public class JwtService {

    private static Logger log = LoggerFactory.getLogger(JwtService.class);

    private final static String JWT_SUBJECT = "jwt.subject";
    private final static String JWT_ISSUER = "jwt.issuer";
    private final static String JWT_LOGIN = "jwt.login";
    private final static String JWT_EMAIL = "jwt.email";
    private final static String JWT_ID = "jwt.id";

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

        log.debug("Login '{}' was parsed successfully", login);
        User user = new User();
        user.setLogin(login);
        return user;
    }

    public String tokenFor(User user) {
        log.debug("Trying to get secret key form SecretKeyProvider");

        byte[] secretKey = secretKeyProvider.getKey();

        log.debug("Trying to build a token for user '{}'", user);

        Date expiration = Date.from(LocalDateTime.now(UTC).plusDays(365).toInstant(UTC));
        return Jwts.builder()
                .setSubject(env.getProperty(JWT_SUBJECT))
                .setExpiration(expiration)
                .setIssuer(env.getProperty(JWT_ISSUER))
                .claim(env.getProperty(JWT_LOGIN), user.getLogin())
                .claim(env.getProperty(JWT_ID), user.getId())
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();
    }


}

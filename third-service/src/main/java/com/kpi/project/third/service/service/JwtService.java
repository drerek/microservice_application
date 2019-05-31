package com.kpi.project.third.service.service;

import com.kpi.project.third.service.dao.UserDao;
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
import org.springframework.stereotype.Component;
import org.springframework.core.env.Environment;

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
    private final UserDao userDao;

    @Autowired
    public JwtService(SecretKeyProvider secretKeyProvider, UserDao userDao) {
        this.secretKeyProvider = secretKeyProvider;
        this.userDao = userDao;
    }

    public User verify(String token) {
        log.debug("Trying to get secret key form SecretKeyProvider");

        byte[] secretKey = secretKeyProvider.getKey();

        log.debug("Trying to parse email from token '{}'", token);

        Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
        String login = claims.getBody().get(env.getProperty(JWT_LOGIN)).toString();

        log.debug("Login '{}' was parsed successfully", login);

        return userDao.findByLogin(login);
    }

    public User verifyForRecoveryPassword(String token) {
        log.debug("Trying to get secret key form SecretKeyProvider");

        byte[] secretKey = secretKeyProvider.getKey();

        log.debug("Trying to parse email from token '{}'", token);

        Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
        String email = claims.getBody().get(env.getProperty(JWT_EMAIL)).toString();

        log.debug("Email '{}' was parsed successfully", email);

        return userDao.findByEmail(email);
    }

}

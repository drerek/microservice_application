package com.kpi.project.second.service.keys;

public final class Key {

    //Exceptions
    public static final String EXCEPTION_AUTHENTICATION = "authentication.exception";
    public static final String EXCEPTION_BAD_TOKEN = "bad.token.exception";
    public static final String EXCEPTION_DATABASE_WORK= "database.work.exception";
    public static final String EXCEPTION_EMAIL_USED= "email.used.exception";
    public static final String EXCEPTION_LOGIN_USED = "login.used.Exception";
    public static final String EXCEPTION_ENTITY_NOT_FOUND = "entity.not.found.exception";
    public static final String EXCEPTION_LOGIN_NOT_FOUND = "login.not.found.exception";
    public static final String EXCEPTION_EMAIL_NOT_FOUND = "email.not.found.exception";
    public static final String EXCEPTION_KEY_NOT_FOUND = "key.not.found.exception";
    public static final String EXCEPTION_FAILED_LOGIN = "failed.login.exception";
    public static final String EXCEPTION_JWT_AUTHENTICATION = "jwt.authentication.exception";
    public static final String EXCEPTION_JWT_IS_NOT_CORRECT = "jwt.bad.token.exception";
    public static final String EXCEPTION_FILE_UPLOAD = "file.upload.exception";
    public static final String EXCEPTION_HASH_ALGORITHM = "hash.algorithm.exception";
    public static final String EXCEPTION_NO_TOKEN = "no.token.exception";
    public static final String EXCEPTION_PARSE_DATE = "parse.date.exception";
    public static final String EXCEPTION_DELETE = "delete.exception";
    public static final String EXCEPTION_UPDATE = "update.exception";
    public static final String EXCEPTION_ITEM_IS_IN_WISHLIST= "item.is.in.wishlist.exception";
    public static final String EXCEPTION_REQUEST_ALREADY_SENT= "request.already.sent.exception";

    //Send mail periods
    public static final String MAIL_MONTHLY="MONTHLY";
    public static final String MAIL_EVERY_SUNDAY="SUNDAY";
    public static final String MAIL_EVERY_MONDAY="MONDAY";
    public static final String MAIL_EVERY_TUESDAY="TUESDAY";
    public static final String MAIL_EVERY_WEDNESDAY="WEDNESDAY";
    public static final String MAIL_EVERY_THURSDATY="THURSDAY";
    public static final String MAIL_EVERY_FRIDAY="FRIDAY";
    public static final String MAIL_EVERY_SATURDAY="SATURDAY";
    public static final String MAIL_DAILY="DAILY";

    //Security url patterns
    public static final String URL_AUTH_PATTERN="auth.all";
    public static final String URL_API_PATTERN="api.all";

    private Key(){}


}

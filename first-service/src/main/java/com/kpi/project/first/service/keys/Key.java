package com.kpi.project.first.service.keys;

public final class Key {

    private static Key instance = null;

    //TABLES
    public static final String TABLE_UUSER = "UUSER";
    public static final String TABLE_FRIEND = "FRIEND";

        //UUSER table fields
    public static final String UUSER_USER_ID ="USER_ID";
    public static final String UUSER_LOGIN ="login";
    public static final String UUSER_PASSWORD ="password";
    public static final String UUSER_NAME ="name";
    public static final String UUSER_SURNAME ="surname";
    public static final String UUSER_EMAIL ="email";
    public static final String UUSER_TIMEZONE ="timezone";
    public static final String UUSER_IMAGE_FILEPATH="image_filepath";
    public static final String UUSER_BDAY="bday";
    public static final String UUSER_PHONE="phone";
    public static final String UUSER_PINED_EVENT_ID="pined_event_id";
    public static final String UUSER_PERIODICAL_EMAIL="periodical_email";
    public static final String UUSER_REGISTER_DATE="register_date";

    //FRIEND table fields
    public static final String FRIEND_SENDER_ID ="SENDER_ID";
    public static final String FRIEND_RECEIVER_ID ="RECEIVER_ID";
    public static final String FRIEND_IS_CONFIRMED="IS_CONFIRMED";

    //UserDao
    public static final String USER_FIND_BY_LOGIN="user.findByLogin";
    public static final String USER_FIND_BY_ID="user.findById";
    public static final String USER_FIND_BY_EMAIL="user.findByEmail";
    public static final String USER_UPDATE="user.update";
    public static final String USER_UPDATE_PASSWORD="user.updatePassword";
    public static final String USER_DELETE="user.delete";
    public static final String USER_GET_ALL_BY_PART = "user.getAllByUsernamePart";
    public static final String USER_GET_FRIENDS_BY_USERNAME_PART = "user.getFriendsByUsernamePart";
    public static final String USER_GET_NOT_FRIENDS_BY_USERNAME_PART = "user.getNotFriendsByUsernamePart";
    public static final String USER_GET_FRIENDS = "user.getFriends";
    public static final String USER_GET_UNCONFIRMED = "user.getUnconfirmedFriends";
    public static final String USER_CONFIRM_FRIEND="user.confirmFriend";
    public static final String USER_DELETE_FRIEND="user.deleteFriend";
    public static final String USER_IS_LOGIN_FREE ="user.isLoginFree";
    public static final String USER_IS_EMAIL_FREE ="user.isEmailFree";
    public static final String USER_GET_BY_EMAIL_PERIOD="user.getByEmailPeriod";
    public static final String USER_DELETE_UNCONFIRMED_ACCOUNTS="user.deleteUnconfirmedAccounts";
    public static final String USER_GET_LOGIN_BY_ID="user.getLoginById";

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
    public static final String EXCEPTION_DELETE = "delete.exception";
    public static final String EXCEPTION_UPDATE = "update.exception";
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

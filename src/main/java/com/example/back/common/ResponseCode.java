package com.example.back.common;

public interface ResponseCode {

    String SUCCESS = "SU";
    String VALIDATION_FAIL = "VF";
    String EMPTY_ORDER = "EO";
    String SIGN_IN_FAIL = "SF";
    String CERTIFICATION_FAIL = "CF";
    String MAIL_FAIL = "MF";
    String DATABASE_ERROR = "DBE";

    String NOT_EXISTED_USER = "NU";
    String NOT_EXISTED_PRODUCT = "NP";
    String NOT_EXISTED_ORDER = "NO";
    String NOT_EXISTED_ANSWER = "NA";
    String NOT_EXISTED_QUESTION = "NQ";
    String NOT_EXISTED_PAYMENT = "NP";

    String WRONG_PASSWORD = "WP";

    String DUPLICATED_PRODUCT_ID = "DPI";
    String DUPLICATED_PRODUCT = "DP";
    String DUPLICATED_ORDER = "DO";
    String DUPLICATE_ID = "DI";
    String DUPLICATED_EMAIL = "DE";
    String DUPLICATED_NICKNAME = "DN";

    String DO_NOT_HAVE_PERMISSION = "NP";
}

package site.zhangsun.utility.exception;

import lombok.Getter;

/**
 * Functions: Http Status Enums
 *
 * @author Murphy Zhang Sun
 * @date 2019-06-13 15:08
 * @since v4.0.1
 */
@Getter
public enum HttpStatusEnums {

    /**
     * OK
     */
    OK(200, "OK"),

    /**
     * Bad Request
     */
    BAD_REQUEST(400, "Bad Request!"),

    /**
     * Internal server error
     */
    INTERNAL_SERVER_ERROR(500, "Service Is Busing, Please contact system manager!"),

    /**
     * Empty request parameter
     */
    EMPTY_PARAM(402, "Empty request parameter!"),

    /**
     * Invalid parameter
     */
    INVALID_PARAM(401, "Invalid Parameter");

    private String msg;
    private int code;

    HttpStatusEnums(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}

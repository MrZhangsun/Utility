package site.zhangsun.utility.exception;

import org.springframework.http.HttpStatus;

/**
 * Functions: Spring Security Authentication Exception..
 *
 * @author Murphy Zhang Sun
 * @date 2019/9/16 14:24
 * @since 4.0.2
 */
public class AuthenticationException extends BaseException {
    public AuthenticationException(HttpStatusEnums httpStatusEnums, String transactionId) {
        super(httpStatusEnums, transactionId);
    }

    public AuthenticationException(String msg, String transactionId) {
        super(HttpStatus.INTERNAL_SERVER_ERROR.value(), msg, transactionId);
    }

    public AuthenticationException(String message) {
        super(HttpStatus.FORBIDDEN.value(), message, null);
    }

    @Override
    public String getMessage() {
        return this.getMsg().concat("  " + this.getTransactionId());
    }
}


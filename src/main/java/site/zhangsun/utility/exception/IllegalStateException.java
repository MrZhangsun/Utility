package site.zhangsun.utility.exception;

import org.springframework.http.HttpStatus;

/**
 * Functions: Illegal State Exception.
 *
 * @author Murphy Zhang Sun
 * @date 2019-07-23 16:31
 * @since v4.0.1
 */
public class IllegalStateException extends BaseException {
    public IllegalStateException(int code, String msg, String transactionId) {
        super(code, msg, transactionId);
    }

    public IllegalStateException(String msg, String transactionId) {
        super(HttpStatus.INTERNAL_SERVER_ERROR.value(), msg, transactionId);
    }

    public IllegalStateException(String msg) {
        super(HttpStatus.INTERNAL_SERVER_ERROR.value(), msg, null);
    }

    public IllegalStateException(HttpStatusEnums httpStatusEnums, String transactionId) {
        super(httpStatusEnums, transactionId);
    }

    @Override
    public String getMessage() {
        return this.getMsg().concat("  " + this.getTransactionId());
    }
}

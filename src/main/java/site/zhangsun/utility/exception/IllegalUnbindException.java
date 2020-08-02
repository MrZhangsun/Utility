package site.zhangsun.utility.exception;

import org.springframework.http.HttpStatus;

/**
 * Functions: Illegal Unbind Data Exception.
 *
 * @author Murphy Zhang Sun
 * @date 2019-07-23 16:31
 * @since v4.0.1
 */
public class IllegalUnbindException extends BaseException {
    public IllegalUnbindException(int code, String msg, String transactionId) {
        super(code, msg, transactionId);
    }

    public IllegalUnbindException(String msg, String transactionId) {
        super(HttpStatus.INTERNAL_SERVER_ERROR.value(), msg, transactionId);
    }

    public IllegalUnbindException(String msg) {
        super(HttpStatus.INTERNAL_SERVER_ERROR.value(), msg, null);
    }

    public IllegalUnbindException(HttpStatusEnums httpStatusEnums, String transactionId) {
        super(httpStatusEnums, transactionId);
    }

    @Override
    public String getMessage() {
        return this.getMsg().concat("  " + this.getTransactionId());
    }
}

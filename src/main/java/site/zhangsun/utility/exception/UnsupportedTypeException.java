package site.zhangsun.utility.exception;

import org.springframework.http.HttpStatus;

/**
 * Functions: Unsupported Type Exception..
 *
 * @author Murphy Zhang Sun
 * @date 2019/9/16 14:24
 * @since 4.0.2
 */
public class UnsupportedTypeException extends BaseException {
    public UnsupportedTypeException(HttpStatusEnums httpStatusEnums, String transactionId) {
        super(httpStatusEnums, transactionId);
    }

    public UnsupportedTypeException(String msg, String transactionId) {
        super(HttpStatus.INTERNAL_SERVER_ERROR.value(), msg, transactionId);
    }

    public UnsupportedTypeException(String message) {
        super(HttpStatus.BAD_REQUEST.value(), message, null);
    }

    @Override
    public String getMessage() {
        return this.getMsg().concat("  " + this.getTransactionId());
    }
}


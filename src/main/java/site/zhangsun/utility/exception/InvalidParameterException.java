package site.zhangsun.utility.exception;

import org.springframework.http.HttpStatus;

/**
 * Functions: Invalid Request Parameter Exception
 *
 * @author Murphy Zhang Sun
 * @date 2019-06-13 15:05
 * @since v4.0.1
 */
public class InvalidParameterException extends BaseException {
    public InvalidParameterException(int code, String msg, String transactionId) {
        super(code, msg, transactionId);
    }

    public InvalidParameterException(HttpStatusEnums httpStatusEnums, String transactionId) {
        super(httpStatusEnums, transactionId);
    }

    public InvalidParameterException(String msg, String transactionId) {
        super(HttpStatus.INTERNAL_SERVER_ERROR.value(), msg, transactionId);
    }

    public InvalidParameterException(String msg) {
        super(HttpStatus.INTERNAL_SERVER_ERROR.value(), msg, null);
    }

    @Override
    public String getMessage() {
        return this.getMsg().concat("  " + this.getTransactionId());
    }
}

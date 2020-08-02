package site.zhangsun.utility.exception;

import org.springframework.http.HttpStatus;

/**
 * Functions: Duplication Binding Exception
 *
 * @author Murphy Zhang Sun
 * @date 2019-06-05 12:40
 * @since v4.0
 */
public class DuplicateBindException extends BaseException {

    public DuplicateBindException(int code, String msg, String transactionId) {
        super(code, msg, transactionId);
    }

    public DuplicateBindException(String msg, String transactionId) {
        super(HttpStatus.INTERNAL_SERVER_ERROR.value(), msg, transactionId);
    }

    public DuplicateBindException(String msg) {
        super(HttpStatus.INTERNAL_SERVER_ERROR.value(), msg, null);
    }

    public DuplicateBindException(HttpStatusEnums httpStatusEnums, String transactionId) {
        super(httpStatusEnums, transactionId);
    }

    @Override
    public String getMessage() {
        return this.getMsg().concat("  " + this.getTransactionId());
    }

}

package site.zhangsun.utility.exception;

import org.springframework.http.HttpStatus;

public class NonLoginException extends BaseException {

    public NonLoginException(int code, String msg, String transactionId) {
        super(code, msg, transactionId);
    }

    public NonLoginException(HttpStatusEnums httpStatusEnums, String transactionId) {
        super(httpStatusEnums, transactionId);
    }

    public NonLoginException(String msg, String transactionId) {
        super(HttpStatus.INTERNAL_SERVER_ERROR.value(), msg, transactionId);
    }

    public NonLoginException(String msg) {
        super(HttpStatus.FORBIDDEN.value(), msg, null);
    }

    @Override
    public String getMessage() {
        return this.getMsg().concat("  " + this.getTransactionId());
    }
}

package site.zhangsun.utility.exception;

import org.springframework.http.HttpStatus;

public class UserForbiddenException extends BaseException {

    public UserForbiddenException(int code, String msg, String transactionId) {
        super(code, msg, transactionId);
    }

    public UserForbiddenException(HttpStatusEnums httpStatusEnums, String transactionId) {
        super(httpStatusEnums, transactionId);
    }

    public UserForbiddenException(String msg, String transactionId) {
        super(HttpStatus.INTERNAL_SERVER_ERROR.value(), msg, transactionId);
    }

    @Override
    public String getMessage() {
        return this.getMsg().concat("  " + this.getTransactionId());
    }

}

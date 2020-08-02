package site.zhangsun.utility.exception;

import org.springframework.http.HttpStatus;

public class UserInvalidException extends BaseException {

    public UserInvalidException(int code, String msg, String transactionId) {
        super(code, msg, transactionId);
    }

    public UserInvalidException(HttpStatusEnums httpStatusEnums, String transactionId) {
        super(httpStatusEnums, transactionId);
    }

    public UserInvalidException(String msg, String transactionId) {
        super(HttpStatus.INTERNAL_SERVER_ERROR.value(), msg, transactionId);
    }

    public UserInvalidException(String msg) {
        super(HttpStatus.INTERNAL_SERVER_ERROR.value(), msg, null);
    }

    @Override
    public String getMessage() {
        return this.getMsg().concat("  " + this.getTransactionId());
    }

}

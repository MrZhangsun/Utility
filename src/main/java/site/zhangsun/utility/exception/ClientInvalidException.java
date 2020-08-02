package site.zhangsun.utility.exception;

import org.springframework.http.HttpStatus;

public class ClientInvalidException extends BaseException {

    public ClientInvalidException(int code, String msg, String transactionId) {
        super(code, msg, transactionId);
    }

    public ClientInvalidException(HttpStatusEnums httpStatusEnums, String transactionId) {
        super(httpStatusEnums, transactionId);
    }

    public ClientInvalidException(String msg, String transactionId) {
        super(HttpStatus.INTERNAL_SERVER_ERROR.value(), msg, transactionId);
    }

    public ClientInvalidException(String msg) {
        super(HttpStatus.FORBIDDEN.value(), msg, null);
    }

    @Override
    public String getMessage() {
        return this.getMsg().concat("  " + this.getTransactionId());
    }
}

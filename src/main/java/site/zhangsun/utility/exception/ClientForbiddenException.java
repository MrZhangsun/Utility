package site.zhangsun.utility.exception;

import org.springframework.http.HttpStatus;

public class ClientForbiddenException extends BaseException {

    public ClientForbiddenException(HttpStatusEnums httpStatusEnums, String transactionId) {
        super(httpStatusEnums, transactionId);
    }

    public ClientForbiddenException(String msg, String transactionId) {
        super(HttpStatus.INTERNAL_SERVER_ERROR.value(), msg, transactionId);
    }

    public ClientForbiddenException(String message) {
        super(HttpStatus.FORBIDDEN.value(), message, null);
    }

    @Override
    public String getMessage() {
        return this.getMsg().concat("  " + this.getTransactionId());
    }
}

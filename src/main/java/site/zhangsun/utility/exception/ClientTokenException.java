package site.zhangsun.utility.exception;

import org.springframework.http.HttpStatus;

public class ClientTokenException extends BaseException {
    public ClientTokenException(int code, String msg, String transactionId) {
        super(code, msg, transactionId);
    }

    public ClientTokenException(HttpStatusEnums httpStatusEnums, String transactionId) {
        super(httpStatusEnums, transactionId);
    }

    public ClientTokenException(String msg, String transactionId) {
        super(HttpStatus.INTERNAL_SERVER_ERROR.value(), msg, transactionId);
    }

    public ClientTokenException(String msg) {
        super(HttpStatus.FORBIDDEN.value(), msg, null);
    }

    @Override
    public String getMessage() {
        return this.getMsg().concat("  " + this.getTransactionId());
    }
}

package site.zhangsun.utility.exception;

import org.springframework.http.HttpStatus;

public class ApplyTaskExcption extends BaseException {

    public ApplyTaskExcption(int code, String msg, String transactionId) {
        super(code, msg, transactionId);
    }

    public ApplyTaskExcption(String msg, String transactionId) {
        super(HttpStatus.INTERNAL_SERVER_ERROR.value(), msg, transactionId);
    }

    public ApplyTaskExcption(HttpStatusEnums httpStatusEnums, String transactionId) {
        super(httpStatusEnums, transactionId);
    }

    public ApplyTaskExcption(String msg) {
        super(HttpStatus.INTERNAL_SERVER_ERROR.value(), msg, null);
    }

    @Override
    public String getMessage() {
        return this.getMsg().concat("  " + this.getTransactionId());
    }
}

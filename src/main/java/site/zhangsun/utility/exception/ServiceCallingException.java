package site.zhangsun.utility.exception;

import org.springframework.http.HttpStatus;

/**
 * Functions: Service Calling Exception
 *
 * @author Murphy Zhang Sun
 * @date 2019-06-26 10:02
 * @since v4.0.1
 */
public class ServiceCallingException extends BaseException {
    public ServiceCallingException(int code, String msg, String transactionId) {
        super(code, msg, transactionId);
    }

    public ServiceCallingException(HttpStatusEnums httpStatusEnums, String transactionId) {
        super(httpStatusEnums, transactionId);
    }

    public ServiceCallingException(String msg, String transactionId) {
        super(HttpStatus.INTERNAL_SERVER_ERROR.value(), msg, transactionId);
    }

    public ServiceCallingException(String msg) {
        super(HttpStatus.INTERNAL_SERVER_ERROR.value(), msg, null);
    }

    @Override
    public String getMessage() {
        return this.getMsg().concat("  " + this.getTransactionId());
    }
}

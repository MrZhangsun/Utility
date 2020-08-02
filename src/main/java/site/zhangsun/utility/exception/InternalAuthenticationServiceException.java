package site.zhangsun.utility.exception;

import org.springframework.http.HttpStatus;


/**
 * Functions: Internal Authentication Service Exception
 *
 * @author Murphy Zhang Sun
 * @date 2019-06-12 19:17
 * @since v1.0.0
 */
public class InternalAuthenticationServiceException extends BaseException {

    public InternalAuthenticationServiceException(int code, String msg, String transactionId) {
        super(code, msg, transactionId);
    }

    public InternalAuthenticationServiceException(String msg, String transactionId) {
        super(HttpStatus.INTERNAL_SERVER_ERROR.value(), msg, transactionId);
    }

    public InternalAuthenticationServiceException(String msg) {
        super(HttpStatus.INTERNAL_SERVER_ERROR.value(), msg, null);
    }

    public InternalAuthenticationServiceException(HttpStatusEnums httpStatusEnums, String transactionId) {
        super(httpStatusEnums, transactionId);
    }

    @Override
    public String getMessage() {
        return this.getMsg().concat("  " + this.getTransactionId());
    }

}

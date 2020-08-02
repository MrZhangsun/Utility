package site.zhangsun.utility.exception;

import org.springframework.http.HttpStatus;

/**
 * Functions: Data inconsistent com.yxt.vehicle.common.exception
 *
 * @author Murphy Zhang Sun
 * @date 2019-06-13 13:52
 * @since v4.0.1
 */
public class DataInconsistentException extends BaseException {

    public DataInconsistentException(int code, String msg, String transactionId) {
        super(code, msg, transactionId);
    }

    public DataInconsistentException(String msg, String transactionId) {
        super(HttpStatus.INTERNAL_SERVER_ERROR.value(), msg, transactionId);
    }

    public DataInconsistentException(HttpStatusEnums httpStatusEnums, String transactionId) {
        super(httpStatusEnums, transactionId);
    }

    public DataInconsistentException(String msg) {
        super(HttpStatus.INTERNAL_SERVER_ERROR.value(), msg, null);
    }

    @Override
    public String getMessage() {
        return this.getMsg().concat("  " + this.getTransactionId());
    }
}

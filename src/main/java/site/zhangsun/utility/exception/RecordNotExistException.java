package site.zhangsun.utility.exception;

import org.springframework.http.HttpStatus;

/**
 * 必要数据记录不存在异常
 *
 * @author zgl
 * @since v4.0.2
 */
public class RecordNotExistException extends BaseException {

    public RecordNotExistException(int code, String msg, String transactionId) {
        super(code, msg, transactionId);
    }

    public RecordNotExistException(String msg, String transactionId) {
        super(HttpStatus.INTERNAL_SERVER_ERROR.value(), msg, transactionId);
    }

    public RecordNotExistException(HttpStatusEnums httpStatusEnums, String transactionId) {
        super(httpStatusEnums, transactionId);
    }

    public RecordNotExistException(String msg) {
        super(HttpStatus.INTERNAL_SERVER_ERROR.value(), msg, null);
    }

    @Override
    public String getMessage() {
        return this.getMsg().concat("  " + this.getTransactionId());
    }
}

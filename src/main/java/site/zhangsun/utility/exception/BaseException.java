package site.zhangsun.utility.exception;

import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * Functions: BaseException
 *
 * @author Murphy Zhang Sun
 * @date 2019-06-05 12:41
 * @since v4.0
 */
@EqualsAndHashCode(callSuper = true)
@Getter
public class BaseException extends RuntimeException {
    private final int code;
    private final String msg;
    private final String transactionId;

    BaseException(int code, String msg, String transactionId) {
        super(msg);
        this.msg = msg;
        this.code = code;
        this.transactionId = transactionId;
    }

    BaseException(HttpStatusEnums httpStatus, String transactionId) {
        super(httpStatus.getMsg());
        this.msg = httpStatus.getMsg();
        this.code = httpStatus.getCode();
        this.transactionId = transactionId;
    }
}

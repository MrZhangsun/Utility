package site.zhangsun.utility.exception;

/**
 * Functions: 获取锁超时异常.
 *
 * @author zhangsunjiankun
 * @date 2020/8/2 下午9:34
 * @since 1.0
 */
public class AcquireLockTimeoutException extends BaseException {
    public AcquireLockTimeoutException(int code, String msg, String transactionId) {
        super(code, msg, transactionId);
    }

    public AcquireLockTimeoutException(HttpStatusEnums httpStatus, String transactionId) {
        super(httpStatus, transactionId);
    }
}

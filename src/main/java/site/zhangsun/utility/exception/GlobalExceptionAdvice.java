package site.zhangsun.utility.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import site.zhangsun.utility.pojo.vo.ResponseVO;

/**
 * Rest Controller Exception Adviser.
 *
 * @author Murphy Zhang Sun
 * @date 2019-09-11
 * @since 4.0.1
 */
@Slf4j
public class GlobalExceptionAdvice {

    @ExceptionHandler(BaseException.class)
    public ResponseVO<Boolean> baseExceptionHandler(BaseException ex) {
        log.error(ex.getMessage(), ex);
        return new ResponseVO<Boolean>().setCode(HttpStatus.BAD_REQUEST.value()).setMsg(ex.getMessage()).setData(false);
    }

    @ExceptionHandler(Exception.class)
    public ResponseVO<Boolean> otherExceptionHandler(Exception ex) {
        ex.printStackTrace();
        return new ResponseVO<Boolean>()
                .setData(false)
                .setCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .setMsg("服务器异常，请稍后重试！"); // HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase()
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseVO<Boolean> httpMessageNotReadableException(HttpMessageNotReadableException ex) {
        log.error(ex.getMessage(), ex);
        return new ResponseVO<Boolean>().setCode(HttpStatus.BAD_REQUEST.value()).setMsg(ex.getMessage()).setData(false);
    }

    @ExceptionHandler(ClientTokenException.class)
    public ResponseVO<Boolean>  clientTokenExceptionHandler(ClientTokenException ex) {
        log.error(ex.getMessage(), ex);
        return new ResponseVO<Boolean>().setCode(ex.getCode()).setMsg(ex.getMsg()).setData(false);
    }

    @ExceptionHandler(NonLoginException.class)
    public ResponseVO<Boolean>  userTokenExceptionHandler(NonLoginException ex) {
        log.error(ex.getMessage(), ex);
        return new ResponseVO<Boolean>().setCode(ex.getCode()).setMsg(ex.getMsg()).setData(false);
    }

    @ExceptionHandler(UserInvalidException.class)
    public ResponseVO<Boolean>  userInvalidExceptionHandler(UserInvalidException ex) {
        log.error(ex.getMessage(), ex);
        return new ResponseVO<Boolean>().setCode(ex.getCode()).setMsg(ex.getMsg()).setData(false);
    }

    @ExceptionHandler(UserForbiddenException.class)
    public ResponseVO<Boolean>  userForbiddenExceptionHandler(UserForbiddenException ex) {
        log.error(ex.getMessage(), ex);
        return new ResponseVO<Boolean>().setCode(ex.getCode()).setMsg(ex.getMsg()).setData(false);
    }

    @ExceptionHandler({InternalAuthenticationServiceException.class})
    public ResponseVO<Boolean> internalAuthenticationServiceException(InternalAuthenticationServiceException e) {
        log.error(e.getMessage());
        return new ResponseVO<Boolean>().setCode(HttpStatus.UNAUTHORIZED.value()).setMsg(e.getMessage()).setData(false);
    }

    @ExceptionHandler({InvalidParameterException.class})
    public ResponseVO<Boolean> invalidParameterException(InvalidParameterException e) {
        log.error(e.getMessage());
        return new ResponseVO<Boolean>().setCode(HttpStatus.BAD_REQUEST.value()).setMsg(e.getMessage()).setData(false);
    }

    @ExceptionHandler({IllegalStateException.class})
    public ResponseVO<Boolean> illegalStateException(IllegalStateException e) {
        log.error(e.getMessage());
        return new ResponseVO<Boolean>().setCode(HttpStatus.BAD_REQUEST.value()).setMsg(e.getMessage()).setData(false);
    }

    @ExceptionHandler({UnsupportedTypeException.class})
    public ResponseVO<Boolean> unsupportedTypeException(UnsupportedTypeException e) {
        log.error(e.getMessage());
        return new ResponseVO<Boolean>().setCode(HttpStatus.BAD_REQUEST.value()).setMsg(e.getMessage()).setData(false);
    }

    @ExceptionHandler({DuplicateRecordException.class, DuplicateBindException.class, IllegalUnbindException.class})
    public ResponseVO<Boolean> duplicateBindException(BaseException e) {
        log.error(e.getMessage());
        return new ResponseVO<Boolean>().setCode(HttpStatus.BAD_REQUEST.value()).setMsg(e.getMessage()).setData(false);
    }

    @ExceptionHandler({ServiceCallingException.class, DataInconsistentException.class})
    public ResponseVO<Boolean> dataInconsistentException(BaseException e) {
        e.printStackTrace();
        return new ResponseVO<Boolean>()
                .setData(false)
                .setCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .setMsg("网络不稳定，请稍后重试！");
    }
}

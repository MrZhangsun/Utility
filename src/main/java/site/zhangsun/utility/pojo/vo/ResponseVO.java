package site.zhangsun.utility.pojo.vo;

import lombok.Data;
import org.springframework.http.HttpStatus;

/**
 * Functions: Response View Object
 *
 * @author Murphy Zhang Sun
 * @date 2019-06-03 17:19
 * @since v4.0
 */
@Data
public class ResponseVO<E> {

    /**
     * 返回状态码
     */
    private Integer code;

    /**
     * 返回消息
     */
    private String msg;

    /**
     * 返回数据
     */
    private E data;

    public ResponseVO() {
    }

    public ResponseVO(Integer code, String msg, E data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public ResponseVO(HttpStatus httpStatus, E data) {
        this.code = httpStatus.value();
        this.msg = httpStatus.getReasonPhrase();
        this.data = data;
    }

    public ResponseVO<E> ok(E data) {
        this.code = HttpStatus.OK.value();
        this.msg = HttpStatus.OK.getReasonPhrase();
        this.data = data;
        return this;
    }

    public ResponseVO<E> error(Integer code, String msg, E data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
        return this;
    }

    public ResponseVO<E> setCode(Integer code) {
        this.code = code;
        return this;
    }

    public ResponseVO<E> setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public ResponseVO<E> setData(E data) {
        this.data = data;
        return this;
    }
}

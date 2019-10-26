package site.zhangsun.utility.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Date;

/**
 * Functions: Terminal View Object
 *
 * @author Murphy Zhang Sun
 * @date 2019-06-03 17:20
 * @since v4.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString
public class TerminalBO extends BaseRowModel {

    /**
     * terminal serial number
     */
    @ExcelProperty(index = 0, value = {"终端信息统计表", "终端编号"})
    private String terminalNum;

    /**
     * terminal supplier
     */
    @ExcelProperty(index = 1, value = {"终端信息统计表", "终端厂商"})
    private String terminalSupplier;

    /**
     * Mobile phone number binding with terminal
     */
    @ExcelProperty(index = 2, value = {"终端信息统计表", "绑定手机号"})
    private String obdBindMobile;

    /**
     * mobile phone SIM card expire date
     */
    @ExcelProperty(index = 3, value = {"终端信息统计表", "SIM卡到期时间"})
    private Date terminalSIMExpireDate;
}
package site.zhangsun.utility.excel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.metadata.Sheet;
import java.io.*;
import java.util.List;

/**
 * Functions: Excel Utility Base On Alibaba Easy Excel.
 *
 * @author Murphy Zhang Sun
 * @date 2019/10/21 13:47
 * @since 0.0.1
 */
public class ExcelUtility {
    private ExcelUtility() {}


    public static void main(String[] args) throws IOException {
        File file = new File("E:\\workspace\\github\\Utility\\src\\main\\resources\\excel\\template.xlsx");
        try (InputStream inputStream = new FileInputStream(file)) {

            AnalysisEventListener<TerminalBO> listener = new RowListener<>();
            List<Object> data = EasyExcelFactory.read(inputStream, new Sheet(1, 0));
            System.out.println(data);
        }

    }
}


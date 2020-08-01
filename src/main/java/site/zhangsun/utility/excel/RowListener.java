package site.zhangsun.utility.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

/**
 * Functions: Row Listener.
 *
 * @author Murphy Zhang Sun
 * @date 2019/10/21 14:15
 * @since 0.0.1
 */
public class RowListener<E> extends AnalysisEventListener<E> {
    @Override
    public void invoke(E o, AnalysisContext analysisContext) {

    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}


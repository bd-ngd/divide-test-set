package com.wky.springboot.myexcel.lisenter;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.wky.springboot.myexcel.dao.IntentSampleDAO;
import com.wky.springboot.myexcel.dto.IntentTemplateDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Author: 吴开云
 * @Date: 2019/12/4 0004
 * @Version： 1.0
 */
public class IntentTemplateListener extends AnalysisEventListener<IntentTemplateDTO> {
    private static final Logger LOGGER = LoggerFactory.getLogger(IntentTemplateListener.class);

    private List<IntentTemplateDTO> list = new ArrayList<IntentTemplateDTO>();


    public IntentTemplateListener() {

    }

    /**
     * 这个每一条数据解析都会来调用
     *
     * @param data
     *            one row value. Is is same as {@link AnalysisContext#readRowHolder()}
     * @param context
     */
    @Override
    public void invoke(IntentTemplateDTO data, AnalysisContext context) {
        list.add(data);
    }

    /**
     * 所有数据解析完成了 都会来调用
     *
     * @param context
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        // 这里也要保存数据，确保最后遗留的数据也存储到数据库
        if(list.size() > 0){
            LOGGER.info("一共读取到 {} 条意图模板",list.size());
        }
    }


    public List<IntentTemplateDTO> getAllList(){
        return list;
    }

}

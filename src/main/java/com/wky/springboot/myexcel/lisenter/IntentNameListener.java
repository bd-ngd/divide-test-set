package com.wky.springboot.myexcel.lisenter;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.wky.springboot.myexcel.dto.IntentNameDTO;
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
public class IntentNameListener extends AnalysisEventListener<IntentNameDTO> {
    private static final Logger LOGGER = LoggerFactory.getLogger(IntentNameListener.class);

    private List<IntentNameDTO> list = new ArrayList<IntentNameDTO>();


    public IntentNameListener() {

    }

    /**
     * 这个每一条数据解析都会来调用
     *
     * @param data
     *            one row value. Is is same as {@link AnalysisContext#readRowHolder()}
     * @param context
     */
    @Override
    public void invoke(IntentNameDTO data, AnalysisContext context) {
        list.add(data);
    }

    /**
     * 所有数据解析完成了 都会来调用
     *
     * @param context
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        if(list.size() > 0){
            LOGGER.info("一共读取到 {} 条意图名称",list.size());
        }
    }


    public List<IntentNameDTO> getAllList(){
        return list;
    }

}

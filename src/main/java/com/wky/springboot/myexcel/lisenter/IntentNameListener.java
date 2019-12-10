package com.wky.springboot.myexcel.lisenter;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.wky.springboot.myexcel.dao.IntentSampleDAO;
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
    /**
     * 每隔5条存储数据库，实际使用中可以3000条，然后清理list ，方便内存回收
     */
    private static final int BATCH_COUNT = 1000;
    private List<IntentNameDTO> nameList = new ArrayList<IntentNameDTO>();
    /**
     * 假设这个是一个DAO，当然有业务逻辑这个也可以是一个service。当然如果不用存储这个对象没用。
     */
    private IntentSampleDAO demoDAO;

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
        nameList.add(data);
    }

    /**
     * 所有数据解析完成了 都会来调用
     *
     * @param context
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        // 这里也要保存数据，确保最后遗留的数据也存储到数据库
        LOGGER.info("意图名称解析完成！一共读取到 {} 条意图名称",nameList.size());
    }


    public List<IntentNameDTO> getAllList(){
        return nameList;
    }

}

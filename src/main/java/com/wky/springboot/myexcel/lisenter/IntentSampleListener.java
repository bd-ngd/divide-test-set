package com.wky.springboot.myexcel.lisenter;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.wky.springboot.myexcel.dao.IntentSampleDAO;
import com.wky.springboot.myexcel.dto.IntentNameDTO;
import com.wky.springboot.myexcel.dto.IntentSampleDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Description:
 * @Author: 吴开云
 * @Date: 2019/12/4 0004
 * @Version： 1.0
 */
public class IntentSampleListener extends AnalysisEventListener<IntentSampleDTO> {
    private static final Logger LOGGER = LoggerFactory.getLogger(IntentSampleListener.class);
    /**
     * 每隔5条存储数据库，实际使用中可以3000条，然后清理list ，方便内存回收
     */
    private static final int BATCH_COUNT = 1000;
    //存放意图名称
    private List<IntentNameDTO> nameList;
    //存放全部意图示例
    private List<List<IntentSampleDTO>> allList;
    //存放90%部分意图示例
    private List<List<IntentSampleDTO>> ninetyList;
    //存放10%部分意图示例
    private List<List<IntentSampleDTO>> tenList;


    public IntentSampleListener() {
        this.nameList  = new ArrayList<>();
    }

    /**
     *  初始化四个list
     * @param list<IntentNameDTO>
     */
    public IntentSampleListener(List<IntentNameDTO> list) {
        this.nameList = list;
        allList = new ArrayList<>(list.size());
        ninetyList = new ArrayList<>(list.size());
        tenList = new ArrayList<>(list.size());
        for (int i = 0; i < list.size(); i++) {
            allList.add(new ArrayList<IntentSampleDTO>());
            ninetyList.add(new ArrayList<IntentSampleDTO>());
            tenList.add(new ArrayList<IntentSampleDTO>());
        }
    }

    /**
     * 这个每一条数据解析都会来调用
     *
     * @param data
     *            one row value. Is is same as {@link AnalysisContext#readRowHolder()}
     * @param context
     */
    @Override
    public void invoke(IntentSampleDTO data, AnalysisContext context) {

    }

    /**
     * 所有数据解析完成了 都会来调用
     *
     * @param context
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {

    }


}

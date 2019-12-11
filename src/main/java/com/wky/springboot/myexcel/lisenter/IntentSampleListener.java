package com.wky.springboot.myexcel.lisenter;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.wky.springboot.myexcel.dto.IntentNameDTO;
import com.wky.springboot.myexcel.dto.IntentSampleDTO;
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
public class IntentSampleListener extends AnalysisEventListener<IntentSampleDTO> {
    private static final Logger LOGGER = LoggerFactory.getLogger(IntentSampleListener.class);

    //存放意图名称
    private List<String> nameList;
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
        nameList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            nameList.add(list.get(i).getId());
        }
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
        //根据ID判断序列号
        Integer index = nameList.indexOf(data.getId());
        //添加至allList对应序列号中
        if (index > -1){
            allList.get(index).add(data);
        }else {
            LOGGER.error("[{}，{}]的意图ID不在列表中",data.getId(),data.getShiLi());
        }
    }

    /**
     * 所有数据解析完成了 都会来调用
     *
     * @param context
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        Integer allSize = 0;
        for (int i = 0; i < allList.size(); i++) {
            allSize += allList.get(i).size();
        }
        if(allSize > 0){
            LOGGER.info("一共读取到 {} 条意图示例",allSize);
        }
        //对每个子List进行划分
        for (int i = 0; i < allList.size(); i++) {
            //遍历allLIst
            List<IntentSampleDTO> listSubNine ;
            List<IntentSampleDTO> listSubTen ;

            //取单次子列表进行划分
            List<IntentSampleDTO> perAllList = allList.get(i);

            //判断本次子列表长度是否大于10个
            Integer ten = perAllList.size() / 10;
            if(ten < 1){
                ten = 1;
            }

            //判断子列表是否大于2个
            if(perAllList.size() > 2){
                listSubTen = perAllList.subList(0, ten);
                listSubNine = perAllList.subList(ten, allList.get(i).size());
            }else {
                listSubNine = perAllList;
                listSubTen = new ArrayList<>();
            }
            tenList.get(i).addAll(listSubTen);
            ninetyList.get(i).addAll(listSubNine);
        }

    }

    /**
     *返回10%部分的数据
     * @return
     */
    public List<IntentSampleDTO> getTen(){
        List<IntentSampleDTO> list = new ArrayList<>();
        for (int i = 0; i < tenList.size(); i++) {
            list.addAll(tenList.get(i));
        }
        if(list.size() > 0){
            LOGGER.info("一共划分 {} 条测试集",list.size());
        }
        return list;
    }

    /**
     * 返回90%部分的数据
     * @return
     */
    public List<IntentSampleDTO> getNinety(){
        List<IntentSampleDTO> list = new ArrayList<>();
        for (int i = 0; i < ninetyList.size(); i++) {
            list.addAll(ninetyList.get(i));
        }
        if(list.size() > 0){
            LOGGER.info("一共划分 {} 条训练集",list.size());
        }
        return list;
    }


}

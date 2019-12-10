package com.wky.springboot.myexcel.dao;

import com.alibaba.excel.EasyExcel;
import com.wky.springboot.myexcel.dto.IntentSampleDTO;
import com.wky.springboot.myexcel.lisenter.IntentSampleListener;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Description:
 * @Author: 吴开云
 * @Date: 2019/12/4 0004
 * @Version： 1.0
 */
@Component
public class IntentSampleDAO {
    public void save(List<IntentSampleDTO> list){
        ;
    }

    /**
     * 最简单的读
     * <p>1. 创建excel对应的实体对象 参照{@link IntentSampleDTO}
     * <p>2. 由于默认一行行的读取excel，所以需要创建excel一行一行的回调监听器，参照{@link IntentSampleListener}
     * <p>3. 直接读即可
     */
    public static List<IntentSampleDTO> readFromExcel(){
// 有个很重要的点 DemoDataListener 不能被spring管理，要每次读取excel都要new,然后里面用到spring可以构造方法传进去
        // 写法1 -- 项目同级目录：
        String fileName = "in.xlsx";
        // 这里 需要指定读用哪个class去读，然后默认读取第一个sheet 文件流会自动关闭
        IntentSampleListener demoDataListener = new IntentSampleListener();
        EasyExcel.read(fileName, IntentSampleDTO.class, demoDataListener).sheet().doRead();

        return null;
    }

    public static void saveToExcel(List<IntentSampleDTO> list){
        String fileName = "out.xlsx";
        // 这里 需要指定写用哪个class去写，然后写到第一个sheet(从0开始)，名字为sheel1 然后文件流会自动关闭
        EasyExcel.write(fileName, IntentSampleDTO.class).sheet(0,"sheel1").doWrite(list);
    }

}

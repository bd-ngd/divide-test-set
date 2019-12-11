package com.wky.springboot.myexcel.dao;

import com.alibaba.excel.EasyExcel;
import com.wky.springboot.myexcel.dto.IntentTemplateDTO;
import com.wky.springboot.myexcel.lisenter.IntentNameListener;
import com.wky.springboot.myexcel.lisenter.IntentTemplateListener;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Description:
 * @Author: 吴开云
 * @Date: 2019/12/4 0004
 * @Version： 1.0
 */
@Component
public class IntentTemplateDAO {

    /**
     * 读取
     */
    public static List<IntentTemplateDTO> readFromExcel(){
        // 项目同级目录：
        String fileName = "in.xlsx";
        // 这里 需要指定读用哪个class去读，然后默认读取第一个sheet 文件流会自动关闭
        IntentTemplateListener listener = new IntentTemplateListener();
        EasyExcel.read(fileName, IntentTemplateDTO.class, listener).sheet(2).doRead();
        return listener.getAllList();
    }

    public static void saveToExcel(List<IntentTemplateDTO> list){
        String fileName = "out.xlsx";
        // 这里 需要指定写用哪个class去写，然后写到第一个sheet(从0开始)，名字为意图模板 然后文件流会自动关闭
        EasyExcel.write(fileName, IntentTemplateDTO.class).sheet(2,"意图模板").doWrite(list);
    }

}

package com.wky.springboot.myexcel.dao;

import com.alibaba.excel.EasyExcel;
import com.wky.springboot.myexcel.dto.IntentNameDTO;
import com.wky.springboot.myexcel.lisenter.IntentNameListener;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Description:
 * @Author: 吴开云
 * @Date: 2019/12/4 0004
 * @Version： 1.0
 */
@Component
public class IntentNameDAO {

    /**
     * 读取
     */
    public static List<IntentNameDTO> readFromExcel(){
        // 项目同级目录：
        String fileName = "in.xlsx";
        // 这里 需要指定读用哪个class去读，然后默认读取第一个sheet 文件流会自动关闭
        IntentNameListener listener = new IntentNameListener();
        EasyExcel.read(fileName, IntentNameDTO.class, listener).sheet().doRead();
        return listener.getAllList();
    }

    public static void saveToExcel(List<IntentNameDTO> list){
        String fileName = "out.xlsx";
        // 这里 需要指定写用哪个class去写，然后写到第一个sheet(从0开始)，名字为意图 然后文件流会自动关闭
        EasyExcel.write(fileName, IntentNameDTO.class).sheet(0,"意图").doWrite(list);
    }

}

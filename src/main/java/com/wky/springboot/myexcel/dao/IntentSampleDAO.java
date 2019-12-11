package com.wky.springboot.myexcel.dao;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.wky.springboot.myexcel.dto.IntentNameDTO;
import com.wky.springboot.myexcel.dto.IntentSampleDTO;
import com.wky.springboot.myexcel.dto.IntentTemplateDTO;
import com.wky.springboot.myexcel.lisenter.IntentSampleListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Author: 吴开云
 * @Date: 2019/12/4 0004
 * @Version： 1.0
 */
@Component
public class IntentSampleDAO {
    private static List<IntentSampleDTO> outTen;
    private static List<IntentSampleDTO> outNinety;
    private static List<IntentNameDTO> nameDTOList;
    private static List<IntentTemplateDTO> templateDTOList;

    /**
     * 读取数据
     */
    public static List<IntentSampleDTO> readFromExcel(){
        String fileName = "in.xlsx";
        //获取意图名称&ID列表
        nameDTOList = IntentNameDAO.readFromExcel();
        //获取意图模板列表
        templateDTOList = IntentTemplateDAO.readFromExcel();
        // 读取意图示例
        IntentSampleListener listener = new IntentSampleListener(nameDTOList);
        EasyExcel.read(fileName, IntentSampleDTO.class, listener).sheet(1).doRead();
        //获取划分的测试集与训练集
        outTen = listener.getTen();
        outNinety = listener.getNinety();
        return null;
    }

    /**
     * 写测试集
     */
    public static void saveToExcelTen(){
        String fileName = "out1.xlsx";
        // 构建写工具实例
        ExcelWriter excelWriter = EasyExcel.write(fileName).build();
        // 写第一个sheet页
        WriteSheet writeSheet = EasyExcel.writerSheet(0,"意图").head(IntentNameDTO.class).build();
        excelWriter.write(nameDTOList, writeSheet);
        // 写第二个sheet页
        WriteSheet writeSheet1 = EasyExcel.writerSheet(1,"意图示例").head(IntentSampleDTO.class).build();
        excelWriter.write(outTen, writeSheet1);
        // 写第三个sheet页
        WriteSheet writeSheet2 = EasyExcel.writerSheet(2,"意图模板").head(IntentTemplateDTO.class).build();
        excelWriter.write(templateDTOList, writeSheet2);
        //关闭流
        excelWriter.finish();
    }

    /**
     * 写训练集
     */
    public static void saveToExcelNinety(){
        String fileName = "out9.xlsx";
        // 构建写工具实例
        ExcelWriter excelWriter = EasyExcel.write(fileName).build();
        // 写第一个sheet页
        WriteSheet writeSheet = EasyExcel.writerSheet(0,"意图").head(IntentNameDTO.class).build();
        excelWriter.write(nameDTOList, writeSheet);
        // 写第二个sheet页
        WriteSheet writeSheet1 = EasyExcel.writerSheet(1,"意图示例").head(IntentSampleDTO.class).build();
        excelWriter.write(outNinety, writeSheet1);
        // 写第三个sheet页
        WriteSheet writeSheet2 = EasyExcel.writerSheet(2,"意图模板").head(IntentTemplateDTO.class).build();
        excelWriter.write(templateDTOList, writeSheet2);
        //关闭流
        excelWriter.finish();
    }

}

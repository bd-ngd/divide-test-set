package com.wky.springboot.myexcel.dao;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.wky.springboot.myexcel.dto.DemoDataDto;
import com.wky.springboot.myexcel.lisenter.DemoDataListener;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.List;

/**
 * @Description:
 * @Author: 吴开云
 * @Date: 2019/12/4 0004
 * @Version： 1.0
 */
@Component
public class DemoDataDAO {
    public void save(List<DemoDataDto> list){
        ;
    }

    /**
     * 最简单的读
     * <p>1. 创建excel对应的实体对象 参照{@link DemoDataDto}
     * <p>2. 由于默认一行行的读取excel，所以需要创建excel一行一行的回调监听器，参照{@link DemoDataListener}
     * <p>3. 直接读即可
     */
    public List<DemoDataDto> readFromExcel(){
// 有个很重要的点 DemoDataListener 不能被spring管理，要每次读取excel都要new,然后里面用到spring可以构造方法传进去
        // 写法1 -- 项目同级目录：
        String fileName = "demo.xlsx";
        // 这里 需要指定读用哪个class去读，然后默认读取第一个sheet 文件流会自动关闭
        DemoDataListener demoDataListener = new DemoDataListener();
        EasyExcel.read(fileName, DemoDataDto.class, demoDataListener).sheet().doRead();

        // 写法2 -- 项目根目录：
        String fileName1 = DemoDataDAO.class.getResource("demo.xlsx").getPath();
        ExcelReader excelReader = EasyExcel.read(fileName, DemoDataDto.class, demoDataListener).build();
        ReadSheet readSheet = EasyExcel.readSheet(0).build();
        excelReader.read(readSheet);
        // 这里千万别忘记关闭，读的时候会创建临时文件，到时磁盘会崩的
        excelReader.finish();
        return demoDataListener.getAllList();
    }

    public void saveToExcel(List<DemoDataDto> list){
        String fileName = "demoOut.xlsx";
        // 这里 需要指定写用哪个class去写，然后写到第一个sheet(从0开始)，名字为sheel1 然后文件流会自动关闭
        EasyExcel.write(fileName, DemoDataDto.class).sheet(0,"sheel1").doWrite(list);
    }

}

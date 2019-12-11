package com.wky.springboot.myexcel.dto;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import lombok.Data;

import java.util.Date;

/**
 * @Description:
 * @Author: 吴开云
 * @Date: 2019/12/4 0004
 * @Version： 1.0
 */
@Data
@HeadRowHeight(15)
@ColumnWidth(25)
public class IntentSampleDTO {
    @ExcelProperty(value = "意图ID", index = 0)
    private String id;
    @ExcelProperty(value = "意图示例", index = 1)
    private String shiLi;
}

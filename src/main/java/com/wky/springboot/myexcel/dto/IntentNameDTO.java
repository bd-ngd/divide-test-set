package com.wky.springboot.myexcel.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @Description:
 * @Author: 吴开云
 * @Date: 2019/12/4 0004
 * @Version： 1.0
 */
@Data
public class IntentNameDTO {
    @ExcelProperty(value = "别名", index = 0)
    private String bieMing;
    @ExcelProperty(value = "描述", index = 1)
    private String miaoShu;
    @ExcelProperty(value = "意图ID", index = 2)
    private String id;
    @ExcelProperty(value = "意图名称", index = 3)
    private String mingCheng;
}

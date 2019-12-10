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
public class IntentTemplateDTO {
    @ExcelProperty(value = "意图ID", index = 0)
    private String id;
    @ExcelProperty(value = "模版", index = 1)
    private String template;
}

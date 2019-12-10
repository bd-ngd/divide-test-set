package com.wky.springboot.myexcel;

import com.wky.springboot.myexcel.dao.IntentSampleDAO;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MyexcelApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyexcelApplication.class, args);
        IntentSampleDAO.saveToExcel(IntentSampleDAO.readFromExcel());
    }

}

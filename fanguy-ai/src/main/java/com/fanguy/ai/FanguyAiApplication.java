package com.fanguy.ai;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("com.fanguy.ai.mapper")
public class FanguyAiApplication {

    public static void main(String[] args) {
        SpringApplication.run(FanguyAiApplication.class, args);
    }

}

package com.edu.reading;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@MapperScan({"com.edu.reading.mapper"})
public class Application {	
    public static void main(String[] agrs) {
        SpringApplication.run(Application.class,agrs);
    }
}

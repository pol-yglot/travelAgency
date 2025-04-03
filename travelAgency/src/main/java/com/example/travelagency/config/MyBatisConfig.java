package com.example.travelagency.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(value = "com.example.travelagency.mapper")
public class MyBatisConfig {

}
package com.example.travelagency;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootApplication
@MapperScan(value = "com.example.travelagency.mapper")
public class TravelAgencyApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(TravelAgencyApplication.class);

    public static void main(String[] args) {
        // 애플리케이션 시작 시 로고 출력
        LOGGER.info(" \n" +
                "  __                    __                             \n" +
                " / /________ __  _____ / / ___ ____ ____ ___  ______ __\n" +
                "/ __/ __/ _ `/ |/ / -_) / / _ `/ _ `/ -_) _ \\/ __/ // /\n" +
                "\\__/_/  \\_,_/|___/\\__/_/  \\_,_/\\_, /\\__/_//_/\\__/\\_, / \n" +
                "                              /___/             /___/  \n ");

        SpringApplication.run(TravelAgencyApplication.class, args);
    }

}

package com.example.travelagency;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;

@SpringBootApplication
@MapperScan(value = "com.example.travelagency.mapper")
public class TravelAgencyApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(TravelAgencyApplication.class);

    public static void main(String[] args) {

        //SpringApplication.run(TravelAgencyApplication.class, args);
        var context = SpringApplication.run(TravelAgencyApplication.class, args);
        // application.yml의 설정된 포트 가져오기
        Environment env = context.getEnvironment();
        String port = env.getProperty("server.port", "8080");

        // 애플리케이션 시작 시 로고 출력
        LOGGER.info(" \n" +
                "  __                    __                             \n" +
                " / /________ __  _____ / / ___ ____ ____ ___  ______ __\n" +
                "/ __/ __/ _ `/ |/ / -_) / / _ `/ _ `/ -_) _ \\/ __/ // /\n" +
                "\\__/_/  \\_,_/|___/\\__/_/  \\_,_/\\_, /\\__/_//_/\\__/\\_, / \n" +
                "                              /___/             /___/  \n ");

        LOGGER.info("✅ 서버가 실행되었습니다.");
        LOGGER.info("🔗 접속 주소: http://localhost:{}", port);

    }
}

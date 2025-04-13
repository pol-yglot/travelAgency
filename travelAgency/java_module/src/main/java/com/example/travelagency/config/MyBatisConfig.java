package com.example.travelagency.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.TypeAliasRegistry;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(value = "com.example.travelagency.mapper")
public class MyBatisConfig {
    /*@Autowired
    public void configure(SqlSessionFactory sqlSessionFactory) {
        TypeAliasRegistry typeAliasRegistry = sqlSessionFactory.getConfiguration().getTypeAliasRegistry();
        typeAliasRegistry.registerAlias("UserVO", com.example.travelagency.vo.UserVO.class);
        typeAliasRegistry.registerAlias("UserDetailVO", com.example.travelagency.vo.UserDetailVO.class);
    }*/
}
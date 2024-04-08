package com.zqswjtu.freemall.product;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 1、整合mybatis-plus
 *    1）导入依赖
 *    <dependency>
 *        <groupId>com.baomidou</groupId>
 *        <artifactId>mybatis-plus-boot-starter</artifactId>
 *        <version>3.2.0</version>
 *    </dependency>
 *    2）配置
 *        1、配置数据源
 *            ①导入数据库驱动
 *            ②在application.yml中配置数据源相关信息
 *        2、配置mybatis-plus
 *            ①使用@MapperrScan
 *            ②告诉mybatis-plus框架sql映射文件位置
 * 2、mybatis-plus使用逻辑删除的方法
 *    1）配置全局的逻辑删除规则
 *    2）配置逻辑删除的组件Bean(3.1.1以后不用配置)
 *    3）需要在实体类对应字段上加上逻辑删除注解@TableLogic
 */
@EnableDiscoveryClient
@MapperScan("com.zqswjtu.freemall.product.dao")
@SpringBootApplication
public class FreemallProductApplication {

    public static void main(String[] args) {
        SpringApplication.run(FreemallProductApplication.class, args);
    }

}

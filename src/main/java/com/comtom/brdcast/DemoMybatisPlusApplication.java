package com.comtom.brdcast;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author zengwei
 */
@EnableSwagger2
@SpringBootApplication(scanBasePackages = {"com.comtom.brdcast.*"})
@MapperScan(basePackages = {"com.comtom.brdcast.dao"})
public class DemoMybatisPlusApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoMybatisPlusApplication.class, args);
    }

}

package cn.ux.jxxt;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("cn.ux.jxxt.dao")
public class JxxtApplication {

    public static void main(String[] args) {
        SpringApplication.run(JxxtApplication.class, args);
    }

}

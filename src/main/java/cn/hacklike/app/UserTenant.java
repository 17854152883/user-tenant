package cn.hacklike.app;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@MapperScan("cn.hacklike.app.mapper")
@ComponentScan({"cn.hacklike"})
@SpringBootApplication
@EnableDiscoveryClient
public class UserTenant {

    public static void main(String[] args) {
        SpringApplication.run(UserTenant.class,args);
    }


}

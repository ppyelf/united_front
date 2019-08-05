package com.iyundao;

import com.iyundao.base.BaseRepositoryImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @ClassName: UnitedFrontApplication
 * @project: IYunDao
 * @author: 念
 * @Date: 8:46 2019/8/5
 * @Description: 启动类
 * @Version: V1.0
 */
@SpringBootApplication
@ComponentScan({
        "com.iyundao.base.*",
        "com.iyundao.entity",
        "com.iyundao.controller",
        "com.iyundao.service",
        "com.iyundao.repository",
})
@EnableJpaRepositories(repositoryBaseClass = BaseRepositoryImpl.class)
public class UnitedFrontApplication {
    public static void main(String[] args) {
        SpringApplication.run(UnitedFrontApplication.class, args);
    }
}

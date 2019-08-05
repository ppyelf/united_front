package com.iyundao;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * @ClassName: ServletInitializer
 * @project: IYunDao
 * @author: 念
 * @Date: 8:46 2019/8/5
 * @Description: 嵌入式Servlet容器
 * @Version: V1.0
 */
public class ServletInitializer extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(UnitedFrontApplication.class);
    }

}

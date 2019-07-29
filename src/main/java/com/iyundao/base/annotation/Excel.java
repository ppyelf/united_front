package com.iyundao.base.annotation;

import java.lang.annotation.*;

/**
 * @ClassName: Excel
 * @project: IYunDao
 * @author: 念
 * @Date: 2019/6/26 9:57
 * @Description: 注解 - excel
 * @Version: V2.0
 */
@Target(value = ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Excel {

    String name() default "";

    int sort() default 0;
}

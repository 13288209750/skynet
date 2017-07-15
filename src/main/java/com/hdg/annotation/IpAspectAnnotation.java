package com.hdg.annotation;

import java.lang.annotation.*;

/**
 * ip验证注解，用于接口的ip验证
 * Created by BlueBuff on 2017/7/15.
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface IpAspectAnnotation {
    String description() default "";
}

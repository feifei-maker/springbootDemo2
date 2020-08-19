package com.whc.springboot.aspect;

import java.lang.annotation.*;

/**
 * ClassName: ServiceAnnotation <br/>
 * Description: <br/>
 * date: 2020/8/19 16:17<br/>
 *
 * @author FEI FEI<br/>
 * @since JDK 1.8
 */
@Target(ElementType.METHOD)
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface ServiceAnnotation {
    String value() default "aaa";
}

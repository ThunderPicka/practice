package xsy.forstudying.practice.aspect.annotation;

import java.lang.annotation.*;

/**
 * @author XuSiYu
 * @version 1.0
 * @date 2021-10-14-10:37
 **/

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface WebLog {
    /**
     * 日志描述信息
     * @return
     */
    String description() default "";
}
package xsy.forstudying.practice.dbconnector.annotation;

import java.lang.annotation.*;

/**
 * @author XuSiYu
 * @version 1.0
 * @date 2022-04-25 15:05
 **/

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface DataSourceServiceAop {

    String key() default "default";
}

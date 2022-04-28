//package xsy.forsutying.practice.dbconnector.aspect;
//
//import lombok.extern.slf4j.Slf4j;
//import org.aspectj.lang.annotation.After;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Before;
//import org.aspectj.lang.annotation.Pointcut;
//import org.springframework.stereotype.Component;
//import org.springframework.web.context.request.RequestContextHolder;
//import org.springframework.web.context.request.ServletRequestAttributes;
//import xsy.forsutying.practice.dbconnector.config.ThreadLocalDataSource;
//
///**
// * @author XuSiYu
// * @version 1.0
// * @date 2022-04-25 14:39
// **/
//@Aspect
//@Component
//@Slf4j
//public class DataSourceAop {
//    /**
//     * 定义切入点
//     * 切入点为有该注解的方法
//     * 此注解用于数据源TEST1
//     */
//    @Pointcut("@annotation(DataSourceServiceAop)")
//    public void serviceTest1DatasourceAspect(){};
//
//    /**
//     * 在切入service方法之前执行
//     * 设置数据源
//     */
//    @Before("serviceTest1DatasourceAspect()")
//    public void beforeAspect(){
//        log.info("切入方法,开始设置数据源");
//        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//        String database_key = attributes.getRequest().getHeader("database_key");
//        ThreadLocalDataSource.setLocalSource(database_key);
//
//
//    }
//    /**
//     * 在切入service方法之后执行
//     * 设置回默认数据源
//     */
//    @After("serviceTest1DatasourceAspect()")
//    public void afterAspect(){
//        log.info("切入方法后,开始切换默认数据源");
//        ThreadLocalDataSource.setLocalSource("default");
//    }
//}
//

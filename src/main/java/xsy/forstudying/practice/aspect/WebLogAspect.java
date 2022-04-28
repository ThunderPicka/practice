package xsy.forstudying.practice.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import xsy.forstudying.practice.aspect.annotation.WebLog;

import java.lang.reflect.Method;

import static java.lang.Character.LINE_SEPARATOR;

/**
 * @author XuSiYu
 * @version 1.0
 * @date 2021-10-14-11:01
 **/

@Aspect
@Component
//@Profile({"dev","test"})
public class WebLogAspect {

    private Logger logger=LoggerFactory.getLogger(this.getClass());

    /**
     * 定义切点
     */
    @Pointcut("@annotation(xsy.forstudying.practice.aspect.annotation.WebLog)")
    public String webLog(){
        //似乎方法里面的代码没有任何用处
        logger.info("这里是切点");
        return "这是切点返回值";
    }

    /**
     * 1、在before之前做的事情，并且指定切点执行时间、
     * 2、around实际上贯穿了整个切面，不仅是在before之前，也可以在after之后接收目标方法返回值
     * @param point
     * @return
     * @throws Throwable
     */
    @Around("webLog()")
    public Object doAround(ProceedingJoinPoint point) throws Throwable {
        logger.info("在Befor之前干点事情");
        long startTime =System.currentTimeMillis();
        //执行切点
        Object[] args = point.getArgs();
        Object[] updateArgs=new Object[args.length];
//        int i=0;
//        for(Object param:args){
//            Object o = param.getClass().getConstructor(param.getClass());
//            updateArgs[i]=o;
//            i++;
//        }
        updateArgs[0]="切面增强后姓名:徐司宇";
        Object result=point.proceed(updateArgs);
        logger.info("切点执行时间为"+(System.currentTimeMillis()-startTime));
        logger.info("在环绕阶段接收目标方法的返回值:"+result);
        //返回接口返回结果
        return result;
    }

    /**
     * 织入切点之前
     * @param point
     */
    @Before("webLog()")
    public void doBefore(JoinPoint point) throws ClassNotFoundException {
        logger.info("========================================== Start ==========================================");
        String pointDescription = getPointDescription(point);
        logger.info(pointDescription);
    }


    @After("webLog()")
    public void doAfter(JoinPoint point){
        // 接口结束后换行，方便分割查看
        logger.info("=========================================== End ===========================================" + LINE_SEPARATOR);
    }

    public String getPointDescription(JoinPoint point) throws ClassNotFoundException {
        //目标方法所属类全路径
        String targetName = point.getTarget().getClass().getName();
        Class targetClass=point.getTarget().getClass();
        //目标方法名
        String methodName=point.getSignature().getName();
        Object[] arguments = point.getArgs();
        Method[] methods = targetClass.getMethods();
        StringBuilder description=new StringBuilder("");
        for(Method method:methods){
            Class[] clazzs=method.getParameterTypes();
            if (clazzs.length == arguments.length) {
                description.append(method.getAnnotation(WebLog.class).description());
                break;
            }
        }
        return null;
    }

}

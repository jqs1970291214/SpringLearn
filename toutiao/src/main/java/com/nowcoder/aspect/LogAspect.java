package com.nowcoder.aspect;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * author:Junqson
 * create:2018/3/26 22:12
 * des:
 */
@Aspect
@Component
public class LogAspect {
    private static final Logger logger = LoggerFactory.getLogger(LogAspect.class);
    //注入controller下面所有方法(使用Ant表达式)
    @Before("execution(* com.nowcoder.controller.*Controller.*(..))")
    public void beforeMethod(JoinPoint joinPoint){
        StringBuilder sb = new StringBuilder();
        for (Object arg : joinPoint.getArgs()) {
            if (arg != null) {
                sb.append("arg:" + arg.toString() + "|");
            }
        }
        logger.info("before time: " + new Date());
        logger.info("before method:" + joinPoint.getSignature().getName() + " " + sb.toString());

    }
//    @After("execution(* com.nowcoder.controller.*Controller.*(..))")
//    public void afterMethod(){
//        logger.info("after method:");
//        logger.info("after time:" + new Date());
//    }
}

package org.example.springaop.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

@Aspect
public class LogAspect {
    private long t1,t2;
    private Logger logger=Logger.getLogger(this.getClass().getName());
    public LogAspect() throws IOException {
        logger.addHandler(new FileHandler("logj.xml"));
        logger.setUseParentHandlers(false);
    }
    @Pointcut("execution(* org.example.springaop.metier.*.*(..))")
    public void pc1(){}
    @Before("pc1()")
    public void beforeApp(JoinPoint joinPoint){
        logger.info("----------------------------");
        t1=System.currentTimeMillis();
        logger.info("Before "+joinPoint.getSignature());
    }
    @After("pc1()")
    public void afterApp(JoinPoint joinPoint){
        t2=System.currentTimeMillis();
        logger.info("After "+joinPoint.getSignature());
        logger.info("Duration:"+(t2-t1));
        logger.info("********************************");
    }

    //private Logger logger = Logger.getLogger(this.getClass().getName());




}

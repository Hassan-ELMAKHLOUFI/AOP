package ma.elmakhloufi.springaop.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Aspect
@EnableAspectJAutoProxy
public class LoggingAspect {

    //@Around("execution(* metier.*.*(..))")
    @Around("@annotation(Log)")
    public Object log(ProceedingJoinPoint joinPoint) {
        Object result=null;
        Date d1 = new Date();
        System.out.println("Before ...." + d1);
        try {
            result = joinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        Date d2 = new Date();
        System.out.println("After .... " + d2);
        System.out.println("Execution Duration : "+(d2.getTime()-d1.getTime()));
        return result;
    }

}

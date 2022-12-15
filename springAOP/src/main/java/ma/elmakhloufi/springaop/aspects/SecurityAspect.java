package ma.elmakhloufi.springaop.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class SecurityAspect {
    @Around(value="@annotation(securedByAspect)",argNames =
            "proceedingJoinPoint,securedByAspect")
    public Object log(ProceedingJoinPoint proceedingJoinPoint, SecuredByAspect
            securedByAspect) {

        String[] roles=securedByAspect.roles();
        boolean authorized=false;
        for (String r:roles){
            if(ApplicationContext.hasRole(r)) authorized=true;
        }
        if(!authorized){
            throw new RuntimeException("Not Authorized");
        }
        else {
            try {
                Object o=proceedingJoinPoint.proceed();
                return o;
            } catch (Throwable throwable) {
                throw new RuntimeException(throwable);
            }
        }
    }
}

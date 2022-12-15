package org.example.springaop.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.example.springaop.entities.Compte;
import org.example.springaop.metier.IMetierImpl;

@Aspect
public aspect PatchAspect {
    @Around("execution(* metier.MetierImpl.retirer(..))&&args(code,mt)")
    public void patch(Long code, double mt, JoinPoint joinPoint, ProceedingJoinPoint
            proceedingJoinPoint) throws Throwable {

        IMetierImpl metier=(IMetierImpl) joinPoint.getTarget();
        Compte cp=metier.getCompte(code);
        if(cp.getSolde()>mt) {
            Object o = proceedingJoinPoint.proceed();
        }
        else
            throw new RuntimeException("Solde insuffisant");

    }
}

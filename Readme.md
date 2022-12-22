# Aspectj
# classe Compte
```java
package org.example.springaop.entities;

public class Compte {
    private Long code ;
    private double solde;

    public Compte(Long code,double solde) {
        this.code = code;
        this.solde = solde;
    }


    public Long getCode() {
        return code;
    }

    public double getSolde() {
        return solde;
    }

    public void setSolde(double solde) {
        this.solde = solde;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "Compte{" +
                "code=" + code +
                ", solde=" + solde +
                '}';
    }
}

```
# interface 
```java
package org.example.springaop.metier;

import org.example.springaop.entities.Compte;

public interface IMetier {
    void addCompte(Compte cp);
    void verser(Long code,double mt);
    void retirer(Long code,double mt);
    Compte getCompte(Long code);


}

```

# impl 
```java
package org.example.springaop.metier;


import org.example.springaop.entities.Compte;

import java.util.HashMap;
import java.util.Map;


public class IMetierImpl implements IMetier {
    private Map<Long,Compte> comptes=new HashMap<>();
    @Override
    public void addCompte(Compte cp) {
        comptes.put(cp.getCode(),cp);
    }
    @Override
    public void verser(Long code, double mt) {
        Compte cp=comptes.get(code);
        cp.setSolde(cp.getSolde()+mt);
    }
    @Override
    public void retirer(Long code, double mt) {
        Compte cp=comptes.get(code);
        cp.setSolde(cp.getSolde()-mt);
    }
    @Override
    public Compte getCompte(Long code) {
        return comptes.get(code);
    }
}

```
# Logging Aspect
```java
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

```
# Auth Aspect
```java
package org.example.springaop.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import java.util.Scanner;

@Aspect
public class SecurityAspect {
    private String username="root";
    private String password="123";
    @Around("execution(* org.example.springaop.test.Main.start(..))")
    public void secureApp(JoinPoint joinPoint, ProceedingJoinPoint proceedingJoinPoint) throws
            Throwable {

        Scanner scanner=new Scanner(System.in);
        System.out.println("Authentication");
        System.out.print("Username :");String username=scanner.next();
        System.out.print("Password :");String pass=scanner.next();
        if(username.equals("root")&&pass.equals("123")){
            System.out.println("Before starting");
            proceedingJoinPoint.proceed();
            System.out.println("End of Application");
        }
        else{
            System.out.println("Access denied ...");
        }
    }
}

```
# Classe de test 
```java
package org.example.springaop.test;

import org.example.springaop.entities.Compte;
import org.example.springaop.metier.IMetier;
import org.example.springaop.metier.IMetierImpl;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
       new Main().start();
    }
    private void start() {
        System.out.println("Starting Application .....");
        IMetier metier=new IMetierImpl();
        Scanner clavier=new Scanner(System.in);
        System.out.print("Code:");
        Long code=clavier.nextLong();
        System.out.print("Solde:");
       double solde=clavier.nextDouble();
        metier.addCompte(new Compte(code,solde));
        while (true){
            try {
                System.out.println("=========================================");
                System.out.println(metier.getCompte(code).toString());
                System.out.print("Type Opération:");String type=clavier.next();
                if(type.equals("q")) break;
                System.out.print("Montant:");double montant=clavier.nextDouble();
                if(type.toLowerCase().equals("v")) metier.verser(code,montant);
                else if(type.toLowerCase().equals("r")) metier.retirer(code,montant);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
```

# Partie 2 Spring AOP
package ma.elmakhloufi.springaop.metier;

import ma.elmakhloufi.springaop.aspects.Log;
import ma.elmakhloufi.springaop.aspects.SecuredByAspect;
import ma.elmakhloufi.springaop.entities.Compte;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class IMetierImpl implements IMetier {
    @Override
    @Log
    public void process() {
        System.out.println("Business processing ...");
    }
    @Override
    @SecuredByAspect(roles = {"ADMIN","USER"})
    public double compute() {
        double data=80;
        System.out.println("Business Computing and returning result ....");
        return data;
    }
    //xz5bcciq
}

package org.example.springaop.metier;

import org.example.springaop.entities.Compte;

public interface IMetier {
    void addCompte(Compte cp);
    void verser(Long code,double mt);
    void retirer(Long code,double mt);
    Compte getCompte(Long code);


}

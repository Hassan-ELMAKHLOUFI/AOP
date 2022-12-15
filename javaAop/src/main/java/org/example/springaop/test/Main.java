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
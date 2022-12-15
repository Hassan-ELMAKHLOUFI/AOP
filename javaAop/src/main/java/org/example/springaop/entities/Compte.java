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

package ma.elmakhloufi.springaop.entities;

public class Compte {
    private Long code ;
    private double solde;

    public Compte(Long code) {
        this.code = code;
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
}

package model;

import java.sql.Timestamp;

public class Emprunt {
    private Date dateEmprunt;
    private Date DateRetour;
    private Date dateLimite;
    private Livre livre;
    private int emprunt_id;
    private int livre_id;

    public Emprunt(int emprunt_id, Timestamp dateEmprunt, Timestamp DateRetour, Timestamp dateLimite, int livre_id){
        
    }


    public Date getDateEmprunt() {
        return dateEmprunt;
    }
    public Date getDateRetour() {
        return DateRetour;
    }
    public Date getDateLimite() {
        return dateLimite;
    }
    public Livre getLivre() {
        return livre;
    }

}

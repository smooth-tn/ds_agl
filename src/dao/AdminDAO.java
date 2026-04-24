package dao;

import model.Compte;

import java.sql.Connection;

public class AdminDAO extends ClientDAO {
    private Connection con = null;

    AdminDAO(Connection con){
        super();

    }


    public void chercherUtilisateur(Compte compte){

    }

    public void creerUtilisateur(Compte compte){

    }

    public void blockUtilsateur(Compte compte){

    }

    public void unblockUtilsateur(Compte compte){

    }
}

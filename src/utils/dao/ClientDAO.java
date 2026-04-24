package src.dao;

import src.modele.Client;
import src.modele.Compte;
import src.utils.SqlQuery;

import java.sql.*;

public class ClientDAO {
    private Connection con = null;

    ClientDAO(){}   //  for heritage purposes only (consult super();)

    ClientDAO(Connection con){
        this.con = con;
    }


    public boolean verifierAuthentification(Compte compte){

        String username = compte.getUsername();
        String password = compte.getMotDePass();

        try{
            PreparedStatement ps = con.prepareStatement(SqlQuery.AUTHONTIFICATION_CLIENT);
            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            return rs.next();       //  if the cursor points to a value = client found 
        }
        catch(SQLException e){
            System.out.println("Erreur d'authentification");
            return false;
        }
        
    }

    public Client chercherClient(Compte compte){
        
        String email = compte.getEmail();

        try{
            PreparedStatement ps = con.prepareStatement(SqlQuery.CHERCHER_CLIENT);
            ps.setString(1, email);

            ResultSet rs = ps.executeQuery();
            if(rs.next())       //  if the cursor points to a value ---> client found 
                return new Client(rs.getInt("id"), rs.getString("username"), rs.getString("email"));
            return null;
        }
        catch(SQLException e){
            System.out.println("Erreur fatale");
            return null;
        }
    }

    public void creerClient(Compte compte){

        String email = compte.getEmail();
        String username = compte.getUsername();
        String password = compte.getMotDePass();

        try{
            PreparedStatement ps = con.prepareStatement(SqlQuery.CREER_CLIENT);
            ps.setString(1, email);
            ps.setString(2, username);
            ps.setString(3, password);

            if(ps.executeUpdate() == 0)
                throw new RuntimeException("Utilisateur existe deja !");
        }
        catch(SQLException e){
            System.out.println("Erreur fatale");
        }
    }

    public boolean modifierPassword(Client client){

        try{
            PreparedStatement ps = con.prepareStatement(SqlQuery.MODIFIER_MOT_DE_PASSE_CLIENT);
            ps.setInt(1, client.getId());

            if(ps.executeUpdate() == 0)
                throw new RuntimeException("Client Introuvable ou mdp invalide (check the conditions) !");
            return true;
        }
        catch(SQLException e){
            System.out.println("Erreur fatale");
            return false;
        }
    }

    public boolean blockClient(int id){
        
        try{
            PreparedStatement ps = con.prepareStatement(SqlQuery.BLOQUER_CLIENT);
            ps.setInt(1, id);

            if(ps.executeUpdate() == 0)
                throw new RuntimeException("Utilisateur n'existe pas !");
            return true;
        }
        catch(SQLException e){
            System.out.println("Erreur fatale");
            return false;
        }
    }

    public void unblockClient(int id){
        try{
            PreparedStatement ps = con.prepareStatement(SqlQuery.DEBLOQUER_CLIENT);
            ps.setInt(1, id);

            if(ps.executeUpdate() == 0)
                throw new RuntimeException("Utilisateur n'existe pas !");
        }
        catch(SQLException e){
            System.out.println("Erreur fatale");
        }
    }

}

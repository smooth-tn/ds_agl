package dao;

import model.*;

import java.sql.*;

public class ClientDAO {
    private Connection con = null;

    ClientDAO(){}   //  for heritage purposes only (consult super();)

    ClientDAO(Connection con){

    }


    public boolean verifierAuthentification(Compte compte){

        String email = compte.getEmail();
        String password = compte.getMotDePass();

        try{
            PreparedStatement ps = con.prepareStatement();
            ps.setString(1, email);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();
            if(rs.next())       //  if the cursor points to a value = client found 
                return true;
            return false;
        }
        catch(SQLException e){
            System.out.println("Erreur d'authentification");
        }
        
    }

    public Client chercherClient(Compte compte){
        
        String email = compte.getEmail();

        try{
            PreparedStatement ps = con.prepareStatement();
            ps.setString(1, email);

            ResultSet rs = ps.executeQuery();
            if(rs.next())       //  if the cursor points to a value ---> client found 
                return new Client(rs.getInt("id"), rs.getString("username"), rs.getString("email"));
            return null;
        }
        catch(SQLException e){
            System.out.println("Erreur fatale");
        }
    }

    public void creerClient(Compte compte){

        String email = compte.getEmail();
        String username = compte.getUsername();
        String password = compte.getMotDePass();

        try{
            PreparedStatement ps = con.prepareStatement();
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

    public void blockClient(Compte compte){
        
    }

    public void unblockClient(Compte compte){

    }

}

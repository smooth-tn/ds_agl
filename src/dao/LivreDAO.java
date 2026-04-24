package dao;

import model.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LivreDAO {

    private Connection con = null;
    
    LivreDAO(Connection con){
        this.con = con;
    }

    public boolean ajouterLivre(Livre livre){

        try{
            PreparedStatement ps = con.prepareStatement();
            ps.setString(1, livre.getTitre());
            ps.setString(2, livre.getAuteur());
            ps.setString(3, livre.getCategorie());

            if(ps.executeUpdate() == 0)
                return false;
            return true;
        }
        catch(SQLException e){
            System.out.println("Erreur fatale");
        }

    }

    public boolean supprimerLivre(Livre livre){
        try{
            PreparedStatement ps = con.prepareStatement();
            ps.setInt(1, livre.getId());

            if(ps.executeUpdate() == 0)
                return false;
            return true;
        }
        catch(SQLException e){
            System.out.println("Erreur fatale");
        }
    }

    public Livre chercherLivre(Livre livre){
        try{
            PreparedStatement ps = con.prepareStatement();
            ps.setInt(1, livre.getId());
            ResultSet rs = ps.executeQuery();

            if(rs.next())
                return new Livre(rs.getString("titre"), rs.getString("auteur"), rs.getString("categorie"));
            return null;           
        }
        catch(SQLException e){
            System.out.println("Erreur fatale");
        }
    }

    public ArrayList<Livre> consulterToutLesLivres(){

        ArrayList<Livre> livres = new ArrayList<Livre>();
        try{
            PreparedStatement ps = con.prepareStatement();
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                Livre temp_livre = new Livre(rs.getString("titre"), rs.getString("auteur"), rs.getString("categorie"));
                livres.add(temp_livre);     
            }
            return livres;
        }
        catch(SQLException e){
            System.out.println("Erreur fatale");
        }
    }

}

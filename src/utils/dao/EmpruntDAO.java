package src.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class EmpruntDAO {
    private Connection con = null;
    EmpruntDAO(Connection con){
        this.con = con;
    }

    public boolean createEmprunt(int clientId, int livreId) {

        try{
            PreparedStatement ps = con.prepareStatement();
            ps.setInt(1, clientId);
            ps.setInt(2, livreId);

            if(ps.executeUpdate() == 0)
                return false;
            return true;

        } catch (Exception e) {
            System.out.println("Erreur Fatale !");
        }
    }

    public boolean retournerLivre(int empruntId) {

        try{
            PreparedStatement ps = con.prepareStatement();
            ps.setInt(1, empruntId);
            
            if(ps.executeUpdate() == 0)
                return false;
            return true;

        } 
        catch (Exception e) {
            System.out.println("Erreur Fatale !");
        }
    }

    public List<Emprunt> getEmpruntsByClient(int clientId) {

        List<Emprunt> list = new ArrayList<>();

        try{
            PreparedStatement ps = con.prepareStatement();
            ps.setInt(1, clientId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Emprunt e = new Emprunt(
                    rs.getInt("id"),
                    rs.getTimestamp("date_emprunt"),
                    rs.getTimestamp("date_retour"),
                    rs.getTimestamp("date_limit"),
                    rs.getInt("livre_id")
                );
                list.add(e);
            }
        }
        catch (Exception e) {
            System.out.println("Erreur Fatale !");
        }
        return list;
    }


}

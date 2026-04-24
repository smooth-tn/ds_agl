package src.dao;

import src.modele.Admin;
import src.utils.SqlQuery;

import java.sql.*;

public class AdminDAO {
    private Connection con = null;

    AdminDAO(Connection con){
        this.con = con;
    }

    public void modifierPassword(Admin admin){

        try{
            PreparedStatement ps = con.prepareStatement(SqlQuery.MODIFIER_ADMIN);
            ps.setInt(1, admin.getId());

            if(ps.executeUpdate() == 0)
                throw new RuntimeException("Admin Introuvable ou mdp invalide (check the conditions) !");
        }
        catch(SQLException e){
            System.out.println("Erreur fatale");
        }
    }

}

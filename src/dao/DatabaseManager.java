package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {

    private Connection con = null;
    private static final String URL = "jdbc:mysql://localhost:3306/BIBLIOTHEQUEDB";
    private static final String USER = "root";
    private static final String PASSWORD = "123";

    private ClientDAO clientDAO;
    private AdminDAO adminDAO;
    private EmpruntDAO empruntDAO;
    private LivreDAO livreDAO;

    public DatabaseManager(){
        initDaoInstances();
    }

    public void reconnectDB(){
        try{
            con = DriverManager.getConnection(URL,USER,PASSWORD);
        }
        catch(SQLException e){
            throw new RuntimeException("\n\nConnection Failed !!!\n");
        }
    }


    public void closeDB(){
        try{
            con.close();
        }
        catch(SQLException e){
            throw new RuntimeException("\n\nClosing Failed !!!\n");
        }
    }

    private void initDaoInstances(){
        ClientDAO clientDAO = new ClientDAO(con);
        AdminDAO adminDAO = new AdminDAO(con);
        EmpruntDAO empruntDAO = new EmpruntDAO(con);
        LivreDAO livreDAO = new LivreDAO(con);
    }


    //  SPLIT THIS LOGIC LATER ON TO SUB DAO LAYERS

    public void chercherEtudiant(int target_id){

    }

    public void chercherEtudiant(String target_username){
        
    }

    public void verifierLogin(){
        
    }

}

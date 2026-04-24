package utils;

public class SqlQuery
 { 
     //requetes clients
     public static final String CREER_CLIENT =
            "INSERT INTO CLIENT(username, password, email) " +
            "VALUES (?, ?, ?)";

      public static final String CHERCHER_CLIENT =
             "SELECT * FROM CLIENT WHERE email = ? AND isBlocked = FALSE";

      public static final String AUTHONTIFICATION_CLIENT =
             "SELECT id, username, email " +
                     "FROM CLIENT " +
                     "WHERE email = ? " +
                     "AND password = ? " +
                     "AND isBlocked = FALSE";

        public static final String MODIFIER_MOT_DE_PASSE_CLIENT =
            "UPDATE CLIENT " +
            "SET password = ? " +
            "WHERE id = ?";

        public static final String BLOQUER_CLIENT =
            "UPDATE CLIENT " +
            "SET isBlocked = TRUE " +
            "WHERE id = ?";

        public static final String DEBLOQUER_CLIENT =
            "UPDATE CLIENT " +
            "SET isBlocked = FALSE " +
            "WHERE id = ?";

        public static final String SUPPRIMER_CLIENT =
        "DELETE FROM CLIENT\n" +
        "WHERE id = ?";



      //requetes livres
      public static final String CHERCHER_LIVRE =
             "SELECT * FROM LIVRE WHERE id = ?";

      public static final String FIND_EMPRUNT_WITH_LIVRE =
            "SELECT e.id, l.titre, e.date_emprunt, e.date_limit, e.date_retour " +
            "FROM EMPRUNT e " +
            "JOIN LIVRE l ON e.livre_id = l.id";

      public static final String INSERT_LIVRE =
            "INSERT INTO livre (titre, auteur, categorie) VALUES (?, ?, ?)";

      public static final String DELETE_LIVRE =
            "DELETE FROM livre WHERE id = ?";

      public static final String FIND_LIVRE_BY_ID =
            "SELECT * FROM livre WHERE id = ?";

      public static final String SELECT_ALL_LIVRES =
            "SELECT * FROM livre";


      //requetes admins
      public static final String MODIFIER_ADMIN =
             "UPDATE ADMINS\n" + 
             "SET password = ?\n" + 
             "WHERE id = ?;";

      public static final String CHERCHER_ADMIN_PAR_EMAIL =
        "SELECT *\n" +
        "FROM ADMINS\n" +
        "WHERE email = ?";

      public static final String LOGIN_ADMIN =
        "SELECT *\n" +
        "FROM ADMINS\n" +
        "WHERE email = ?\n" +
        "AND password = ?";

      public static final String AJOUTER_ADMIN =
            "INSERT INTO ADMINS\n" +
            "(username, password, email)\n" +
            "VALUES (?, ?, ?)";

       public static final String AFFICHER_ADMINS =
            "SELECT *\n" +
            "FROM ADMINS";

        public static final String SUPPRIMER_ADMIN =
            "DELETE FROM ADMINS\n" +
            "WHERE id = ?";




     //requetes emprunt
     public static final String CREATE_EMPRUNT =
            "INSERT INTO EMPRUNT(date_emprunt, date_limit, livre_id) " +
            "VALUES (CURRENT_TIMESTAMP, ?, ?)";

     public static final String GET_EMPRUNTS_BY_CLIENT =
            "SELECT e.id, e.date_emprunt, e.date_retour, e.date_limit, e.livre_id " +
            "FROM EMPRUNT e " +
            "JOIN LIVRE l ON e.livre_id = l.id " +
            "WHERE e.livre_id IN (SELECT id FROM LIVRE)";
    
     public static final String GET_EMPRUNT_BY_ID =
            "SELECT * FROM EMPRUNT WHERE id = ?";

    


                                                          
                     

 }
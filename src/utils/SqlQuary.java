package utils;

public class SqlQuery
 {
     public static final String LOGIN_CLIENT =
             "SELECT * FROM CLIENT WHERE email = ? AND isBlocked = FALSE";

     public static final String CHERCHER_CLIENT =
             "SELECT * FROM CLIENT WHERE email = ? AND isBlocked = FALSE";


     public static final String CHERCHER_LIVRE =
             "SELECT * FROM LIVRE WHERE id = ?";


     public static final String AUTHONTIFICATION_CLIENT =
             "SELECT id, username, email " +
                     "FROM CLIENT " +
                     "WHERE email = ? " +
                     "AND password = ? " +
                     "AND isBlocked = FALSE";

 }
package src.service;

import src.dao.AdminDAO;
import src.dao.DatabaseManager;
import src.modele.Admin;

public class AdminService {
    private final AdminDAO adminDAO;
    private final DatabaseManager db;

    public AdminService() {
        this.db = DatabaseManager.getInstance();
        // connection already done by ClientService (singleton), but ensure it's open
        if (db.getConnection() == null) db.connect();
        this.adminDAO = new AdminDAO(db.getConnection());
    }

    public Admin authenticate(String username, String password) {
        return adminDAO.authentifier(username, password);
    }

    public boolean changePassword(int adminId, String username, String oldPwd, String newPwd, String confirm) {
        if (!newPwd.equals(confirm) || newPwd.length() < 4) return false;
        Admin verif = adminDAO.authentifier(username, oldPwd);
        if (verif == null || verif.getId() != adminId) return false;
        return adminDAO.modifierPassword(new Admin(adminId,newPwd,username));
    }
}
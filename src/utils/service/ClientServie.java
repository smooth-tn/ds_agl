package src.service;

import src.dao.ClientDAO;
import src.dao.DatabaseManager;
import src.modele.Client;
import src.modele.Compte;
import src.utils.EmailValidation;

import java.util.List;

public class ClientService {
    private final ClientDAO clientDAO;
    private final DatabaseManager db;

    public ClientService() {
        this.db = DatabaseManager.getInstance();
        db.connect();
        db.initSchema(); // ensures tables exist and default admin/books
        this.clientDAO = new ClientDAO(db.getConnection());
    }

    public boolean testConnection() {
        return db.getConnection() != null;
    }

    public Client authenticate(String username, String password) {
        return clientDAO.authentifier(username, password);
    }

    public boolean register(String username, String email, String password) {
        if (username.isEmpty() || email.isEmpty() || password.isEmpty()) return false;
        if (!EmailValidation.isValidEmail(email)) return false;
        if (password.length() < 4) return false;
        Compte compte = new Compte(username, email, password);
        return clientDAO.creerClient(compte);
    }

    public boolean changePassword(int clientId, String username, String oldPwd, String newPwd, String confirm) {
        if (!newPwd.equals(confirm) || newPwd.length() < 4) return false;
        Client verif = clientDAO.authentifier(username, oldPwd);
        if (verif == null || verif.getId() != clientId) return false;
        return clientDAO.modifierPassword(new Client(clientId,username, verif.getEmail()));
    }

    public boolean blockClient(int clientId) {
        return clientDAO.bloquerClient(clientId);
    }

    public boolean unblockClient(int clientId) {
        return clientDAO.debloquerClient(clientId);
    }

    public List<Client> getAllClients() {
        return clientDAO.tousLesClients();
    }

    public Client getClientById(int id) {
        return clientDAO.chercherParId(id);
    }

    public void shutdown() {
        db.disconnect();
    }
}
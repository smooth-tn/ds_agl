package src.service;

import src.dao.EmpruntDAO;
import src.dao.LivreDAO;
import src.dao.DatabaseManager;
import src.modele.Emprunt;
import java.util.List;

public class BorrowService {
    private final EmpruntDAO empruntDAO;
    private final LivreDAO livreDAO;

    public BorrowService() {
        DatabaseManager db = DatabaseManager.getInstance();
        if (db.getConnection() == null) db.connect();
        this.empruntDAO = new EmpruntDAO(db.getConnection());
        this.livreDAO   = new LivreDAO(db.getConnection());
    }

    public boolean borrowBook(int clientId, int bookId) {
        var livre = livreDAO.chercherParId(bookId);
        if (livre == null || !livre.isDispo()) return false;
        // check if client already has this book active
        List<Emprunt> actifs = empruntDAO.empruntsActifsClient(clientId);
        for (Emprunt e : actifs) {
            if (e.getLivreId() == bookId) return false;
        }
        boolean ok = empruntDAO.emprunterLivre(clientId, bookId);
        if (ok) livreDAO.setDispo(bookId, false);
        return ok;
    }

    public boolean returnBook(int empruntId, int clientId) {
        // verify ownership
        List<Emprunt> actifs = empruntDAO.empruntsActifsClient(clientId);
        boolean owns = actifs.stream().anyMatch(e -> e.getId() == empruntId);
        if (!owns) return false;
        boolean ok = empruntDAO.retournerLivre(empruntId);
        if (ok) {
            // get livreId from the emprunt
            Emprunt e = actifs.stream().filter(em -> em.getId() == empruntId).findFirst().orElse(null);
            if (e != null) livreDAO.setDispo(e.getLivreId(), true);
        }
        return ok;
    }

    public List<Emprunt> getActiveByClient(int clientId) {
        return empruntDAO.empruntsActifsClient(clientId);
    }

    public List<Emprunt> getHistoryByClient(int clientId) {
        return empruntDAO.historiqueClient(clientId);
    }

    public List<Emprunt> getAllActive() {
        return empruntDAO.tousEmpruntsActifs();
    }
}

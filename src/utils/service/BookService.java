package src.service;

import src.dao.LivreDAO;
import src.dao.DatabaseManager;
import src.modele.Livre;
import java.util.List;

public class BookService {
    private final LivreDAO livreDAO;

    public BookService() {
        DatabaseManager db = DatabaseManager.getInstance();
        db.reconnectDB();
        this.livreDAO = new LivreDAO(db.getConnection());
    }

    public List<Livre> getAllBooks() {
        return livreDAO.tousLesLivres();
    }

    public List<Livre> searchBooks(String keyword) {
        return livreDAO.rechercherParTitre(keyword);
    }

    public boolean addBook(String title, String author, String category) {
        if (title.isEmpty() || author.isEmpty() || category.isEmpty()) return false;
        Livre livre = new Livre(title, author, category);
        return livreDAO.ajouterLivre(livre);
    }

    public boolean deleteBook(int bookId) {
        return livreDAO.supprimerLivre(bookId);
    }

    public void setAvailability(int bookId, boolean available) {
        livreDAO.setDispo(bookId, available);
    }

    public Livre getBookById(int id) {
        return livreDAO.chercherParId(id);
    }
}
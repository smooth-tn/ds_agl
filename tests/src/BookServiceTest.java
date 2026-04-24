import service.BookService;
import model.Livre;
import java.util.List;

public class BookServiceTest {
    public void testAddAndDeleteBook() {
        BookService service = new BookService();
        String title = "Test Book " + System.currentTimeMillis();
        boolean added = service.addBook(title, "Test Author", "Test");
        assert added : "Ajout de livre échoué";
        List<Livre> books = service.searchBooks(title);
        assert !books.isEmpty() : "Livre non trouvé après ajout";
        int id = books.get(0).getId();
        boolean deleted = service.deleteBook(id);
        assert deleted : "Suppression échouée";
        Livre after = service.getBookById(id);
        assert after == null : "Livre existe encore après suppression";
    }
}
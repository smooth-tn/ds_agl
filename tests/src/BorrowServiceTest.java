import service.*;
import model.Livre;
import model.Emprunt;
import java.util.List;

public class BorrowServiceTest {
    public void testBorrowAndReturn() {
        ClientService clientService = new ClientService();
        BookService bookService = new BookService();
        BorrowService borrowService = new BorrowService();

        // create test client
        String username = "borrower_" + System.currentTimeMillis();
        clientService.register(username, "borrow@test.com", "pwd");
        var client = clientService.authenticate(username, "pwd");
        // add a test book
        String bookTitle = "BorrowTest " + System.currentTimeMillis();
        bookService.addBook(bookTitle, "Author", "Test");
        var books = bookService.searchBooks(bookTitle);
        assert !books.isEmpty();
        int bookId = books.get(0).getId();
        // borrow
        boolean borrowed = borrowService.borrowBook(client.getId(), bookId);
        assert borrowed : "Emprunt échoué";
        // check active
        List<Emprunt> actifs = borrowService.getActiveByClient(client.getId());
        assert actifs.stream().anyMatch(e -> e.getLivreId() == bookId) : "Emprunt non enregistré";
        // return
        int empruntId = actifs.get(0).getId();
        boolean ret = borrowService.returnBook(empruntId, client.getId());
        assert ret : "Retour échoué";
        // check dispo
        Livre livre = bookService.getBookById(bookId);
        assert livre.isDispo() : "Livre non disponible après retour";

        clientService.shutdown();
    }
}
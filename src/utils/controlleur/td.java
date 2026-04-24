package src.controlleur;

import src.modele.Admin;
import src.modele.Client;
import src.service.AdminService;
import src.service.BookService;
import src.service.BorrowService;
import src.service.ClientService;
import src.view.View;

public class AppController {
    private final ClientService clientService;
    private final AdminService adminService;
    private final BookService bookService;
    private final BorrowService borrowService;

    public AppController() {
        // DatabaseManager is a singleton – tables created automatically
        this.clientService = new ClientService();
        this.adminService  = new AdminService();
        this.bookService   = new BookService();
        this.borrowService = new BorrowService();
    }

    public void run() {
        View.afficherBienvenue();

        // Test connection before starting
        if (!clientService.testConnection()) {
            View.erreur("Impossible de se connecter à la base de données.");
            View.erreur("Vérifiez que MySQL est démarré et que les identifiants dans DatabaseManager sont corrects.");
            System.exit(1);
        }

        boolean running = true;
        while (running) {
            int choix = View.afficherMenuConnexion();
            switch (choix) {
                case 1 -> loginClient();
                case 2 -> loginAdmin();
                case 3 -> inscription();
                case 0 -> running = false;
                default -> View.erreur("Choix invalide.");
            }
        }
        clientService.shutdown();
    }

    private void loginClient() {
        String[] creds = View.formulaireConnexion();
        var client = clientService.authenticate(creds[0], creds[1]);
        if (client == null) {
            View.erreur("Username/mot de passe incorrect ou compte bloqué.");
            View.pause();
            return;
        }
        View.succes("Bienvenue, " + client.getUserName() + " !");
        menuClient(client);
    }

    private void loginAdmin() {
        String[] creds = View.formulaireConnexion();
        var admin = adminService.authenticate(creds[0], creds[1]);
        if (admin == null) {
            View.erreur("Identifiants administrateur incorrects.");
            View.pause();
            return;
        }
        View.succes("Bienvenue, Administrateur " + admin.getUserName() + " !");
        menuAdmin(admin);
    }

    private void inscription() {
        String[] data = View.formulaireInscription();
        if (clientService.register(data[0], data[1], data[2])) {
            View.succes("Compte créé avec succès ! Vous pouvez maintenant vous connecter.");
        } else {
            View.erreur("Échec de la création du compte (username ou email déjà utilisé).");
        }
        View.pause();
    }

    // --------------------- Client menu ---------------------
    private void menuClient(Client client) {
        boolean connecte = true;
        while (connecte) {
            int choix = View.afficherMenuClient(client.getUserName());
            switch (choix) {
                case 1 -> View.afficherLivres(bookService.getAllBooks());
                case 2 -> rechercherLivre();
                case 3 -> emprunterLivre(client.getId());
                case 4 -> retournerLivre(client.getId());
                case 5 -> View.afficherEmprunts(borrowService.getActiveByClient(client.getId()));
                case 6 -> View.afficherEmprunts(borrowService.getHistoryByClient(client.getId()));
                case 7 -> changerMdpClient(client.getId(), client.getUserName());
                case 0 -> connecte = false;
                default -> View.erreur("Choix invalide.");
            }
        }
    }

    private void rechercherLivre() {
        String titre = View.lireChaine("  Entrez le titre (ou une partie) : ");
        View.afficherLivres(bookService.searchBooks(titre));
        View.pause();
    }

    private void emprunterLivre(int clientId) {
        View.afficherLivres(bookService.getAllBooks());
        int livreId = View.lireEntier("  ID du livre à emprunter (0=annuler) : ");
        if (livreId == 0) return;
        boolean ok = borrowService.borrowBook(clientId, livreId);
        if (ok) View.succes("Emprunt enregistré ! Durée : 14 jours.");
        else View.erreur("Impossible d'emprunter (livre indisponible, déjà emprunté ou inexistant).");
        View.pause();
    }

    private void retournerLivre(int clientId) {
        var actifs = borrowService.getActiveByClient(clientId);
        if (actifs.isEmpty()) {
            View.info("Aucun emprunt actif.");
            View.pause();
            return;
        }
        View.afficherEmprunts(actifs);
        int empruntId = View.lireEntier("  ID de l'emprunt à retourner (0=annuler) : ");
        if (empruntId != 0 && borrowService.returnBook(empruntId, clientId)) {
            View.succes("Retour enregistré.");
        } else if (empruntId != 0) {
            View.erreur("Échec du retour.");
        }
        View.pause();
    }

    private void changerMdpClient(int clientId, String username) {
        String[] data = View.formulaireChangerMdp();
        if (clientService.changePassword(clientId, username, data[0], data[1], data[2])) {
            View.succes("Mot de passe modifié.");
        } else {
            View.erreur("Échec (ancien mot de passe incorrect ou validation échouée).");
        }
        View.pause();
    }

    // --------------------- Admin menu ---------------------
    private void menuAdmin(Admin admin) {
        boolean connecte = true;
        while (connecte) {
            int choix = View.afficherMenuAdmin(admin.getUserName());
            switch (choix) {
                case 1 -> View.afficherLivres(bookService.getAllBooks());
                case 2 -> adminAjouterLivre();
                case 3 -> adminSupprimerLivre();
                case 4 -> rechercherLivre();
                case 5 -> View.afficherClients(clientService.getAllClients());
                case 6 -> adminBloquerClient();
                case 7 -> adminDebloquerClient();
                case 8 -> View.afficherEmprunts(borrowService.getAllActive());
                case 9 -> changerMdpAdmin(admin.getId(), admin.getUserName());
                case 0 -> connecte = false;
                default -> View.erreur("Choix invalide.");
            }
        }
    }

    private void adminAjouterLivre() {
        String[] data = View.formulaireNouveauLivre();
        if (bookService.addBook(data[0], data[1], data[2]))
            View.succes("Livre ajouté.");
        else
            View.erreur("Erreur lors de l'ajout.");
        View.pause();
    }

    private void adminSupprimerLivre() {
        View.afficherLivres(bookService.getAllBooks());
        int id = View.lireEntier("  ID du livre à supprimer (0=annuler) : ");
        if (id == 0) return;
        if (bookService.deleteBook(id))
            View.succes("Livre supprimé.");
        else
            View.erreur("Impossible de supprimer (livre emprunté ou inexistant).");
        View.pause();
    }

    private void adminBloquerClient() {
        View.afficherClients(clientService.getAllClients());
        int id = View.lireEntier("  ID du client à bloquer : ");
        if (clientService.blockClient(id))
            View.succes("Client bloqué.");
        else
            View.erreur("Client introuvable ou déjà bloqué.");
        View.pause();
    }

    private void adminDebloquerClient() {
        View.afficherClients(clientService.getAllClients());
        int id = View.lireEntier("  ID du client à débloquer : ");
        if (clientService.unblockClient(id))
            View.succes("Client débloqué.");
        else
            View.erreur("Client introuvable ou non bloqué.");
        View.pause();
    }

    private void changerMdpAdmin(int adminId, String username) {
        String[] data = View.formulaireChangerMdp();
        if (adminService.changePassword(adminId, username, data[0], data[1], data[2]))
            View.succes("Mot de passe administrateur modifié.");
        else
            View.erreur("Échec (ancien mot de passe incorrect).");
        View.pause();
    }
}

package src.view;

import java.util.List;
import java.util.Scanner;

public class View {

    private static final Scanner sc = new Scanner(System.in);

    // ─────────────────────────── HELPERS ───────────────────────────

    public static void ligne() {
        System.out.println("────────────────────────────────────────────────────────────");
    }

    public static void ligneDouble() {
        System.out.println("════════════════════════════════════════════════════════════");
    }

    public static void pause() {
        System.out.print("\n  [Appuyez sur Entrée pour continuer...]");
        sc.nextLine();
    }

    public static void succes(String msg) { System.out.println("  ✓ " + msg); }
    public static void erreur(String msg)  { System.out.println("  ✗ " + msg); }
    public static void info(String msg)    { System.out.println("  → " + msg); }

    public static int lireEntier(String prompt) {
        while (true) {
            System.out.print(prompt);
            try { return Integer.parseInt(sc.nextLine().trim()); }
            catch (NumberFormatException e) { erreur("Entrée invalide. Veuillez entrer un nombre."); }
        }
    }

    public static String lireChaine(String prompt) {
        System.out.print(prompt);
        return sc.nextLine().trim();
    }

    // ─────────────────────────── MENUS PRINCIPAUX ───────────────────────────

    public static void afficherBienvenue() {
        ligneDouble();
        System.out.println("         📚  SYSTÈME DE GESTION DE BIBLIOTHÈQUE  📚");
        ligneDouble();
    }

    public static int afficherMenuConnexion() {
        ligneDouble();
        System.out.println("                   ACCUEIL");
        ligne();
        System.out.println("  1. Se connecter (Client)");
        System.out.println("  2. Se connecter (Administrateur)");
        System.out.println("  3. Créer un compte client");
        System.out.println("  0. Quitter");
        ligne();
        return lireEntier("  Votre choix : ");
    }

    public static int afficherMenuClient(String username) {
        ligneDouble();
        System.out.println("               MENU CLIENT — " + username.toUpperCase());
        ligne();
        System.out.println("  1. Consulter le catalogue (tous les livres)");
        System.out.println("  2. Rechercher un livre par titre");
        System.out.println("  3. Emprunter un livre");
        System.out.println("  4. Retourner un livre");
        System.out.println("  5. Mes emprunts actifs");
        System.out.println("  6. Mon historique d'emprunts");
        System.out.println("  7. Changer mon mot de passe");
        System.out.println("  0. Se déconnecter");
        ligne();
        return lireEntier("  Votre choix : ");
    }

    public static int afficherMenuAdmin(String username) {
        ligneDouble();
        System.out.println("            MENU ADMINISTRATEUR — " + username.toUpperCase());
        ligne();
        System.out.println("  ── GESTION LIVRES ──");
        System.out.println("  1. Consulter tous les livres");
        System.out.println("  2. Ajouter un livre");
        System.out.println("  3. Supprimer un livre");
        System.out.println("  4. Rechercher un livre par titre");
        System.out.println();
        System.out.println("  ── GESTION CLIENTS ──");
        System.out.println("  5. Lister tous les clients");
        System.out.println("  6. Bloquer un client");
        System.out.println("  7. Débloquer un client");
        System.out.println();
        System.out.println("  ── GESTION EMPRUNTS ──");
        System.out.println("  8. Voir tous les emprunts actifs");
        System.out.println();
        System.out.println("  ── COMPTE ──");
        System.out.println("  9. Changer mon mot de passe");
        System.out.println("  0. Se déconnecter");
        ligne();
        return lireEntier("  Votre choix : ");
    }

    // ─────────────────────────── FORMULAIRES ───────────────────────────

    public static String[] formulaireConnexion() {
        ligne();
        System.out.println("  CONNEXION");
        ligne();
        String username = lireChaine("  Username : ");
        String password = lireChaine("  Mot de passe : ");
        return new String[]{username, password};
    }

    public static String[] formulaireInscription() {
        ligne();
        System.out.println("  INSCRIPTION — Nouveau compte client");
        ligne();
        String username = lireChaine("  Username : ");
        String email    = lireChaine("  Email    : ");
        String password = lireChaine("  Mot de passe : ");
        return new String[]{username, email, password};
    }

    public static String[] formulaireNouveauLivre() {
        ligne();
        System.out.println("  AJOUTER UN LIVRE");
        ligne();
        String titre    = lireChaine("  Titre    : ");
        String auteur   = lireChaine("  Auteur   : ");
        String categorie= lireChaine("  Catégorie: ");
        return new String[]{titre, auteur, categorie};
    }

    public static String[] formulaireChangerMdp() {
        ligne();
        System.out.println("  CHANGER MOT DE PASSE");
        ligne();
        String ancien = lireChaine("  Ancien mot de passe : ");
        String nouveau= lireChaine("  Nouveau mot de passe : ");
        String confirm= lireChaine("  Confirmer le nouveau : ");
        return new String[]{ancien, nouveau, confirm};
    }

    // ─────────────────────────── AFFICHAGE DONNÉES ───────────────────────────

    public static void afficherLivres(List<Livre> livres) {
        if (livres.isEmpty()) {
            info("Aucun livre trouvé.");
            return;
        }
        ligne();
        System.out.printf("  %-4s %-35s %-22s %-16s %s%n",
                "ID", "TITRE", "AUTEUR", "CATÉGORIE", "DISPO");
        ligne();
        for (Livre l : livres)
            System.out.println("  " + l);
        ligne();
        info(livres.size() + " livre(s) affiché(s).");
    }

    public static void afficherClients(List<Client> clients) {
        if (clients.isEmpty()) {
            info("Aucun client enregistré.");
            return;
        }
        ligne();
        System.out.printf("  %-4s %-22s %-32s %s%n", "ID", "USERNAME", "EMAIL", "STATUT");
        ligne();
        for (Client c : clients)
            System.out.println("  " + c);
        ligne();
        info(clients.size() + " client(s) affiché(s).");
    }

    public static void afficherEmprunts(List<Emprunt> emprunts) {
        if (emprunts.isEmpty()) {
            info("Aucun emprunt trouvé.");
            return;
        }
        ligne();
        for (Emprunt e : emprunts)
            System.out.println("  " + e);
        ligne();
        info(emprunts.size() + " emprunt(s) affiché(s).");
    }
}

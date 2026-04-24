package view;


public class View {

    public static void afficherLogin() {
        System.out.println("=== Interface de Connexion ===");
        System.out.println("Choisissez votre rôle :");
        System.out.println("1. Client");
        System.out.println("2. Administrateur");
        System.out.println("3. S'inscrire (nouveau compte)");
        System.out.print("Votre choix : ");
    }

    public static void afficherMenuClient() {
        System.out.println("\n=== Menu Client ===");
        System.out.println("Bienvenue cher client !");
        System.out.println("1. Commander un livre");
        System.out.println("2. Emprunter un livre");
        System.out.println("3. Rechercher un livre par titre");
        System.out.println("4. Se déconnecter");
        System.out.print("Votre choix : ");
    }

    public static void afficherMenuAdmin() {
        System.out.println("\n=== Menu Administrateur ===");
        System.out.println("Bienvenue administrateur !");
        System.out.println("1. Bloquer un client");
        System.out.println("2. Ajouter un livre");
        System.out.println("3. Supprimer un livre");
        System.out.println("4. Voir le nombre total de livres dans la BD");
        System.out.println("5. Se déconnecter");
        System.out.print("Votre choix : ");
    }



}
package modele;

public class NullPasswordException extends RuntimeException {
    public NullPasswordException() {
        super("Le mot de passe ne peut pas être nul.");
    }

}

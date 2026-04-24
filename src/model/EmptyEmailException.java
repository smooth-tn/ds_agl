package model;

public class EmptyEmailException extends RuntimeException {
    public EmptyEmailException() {
        super("L'email ne peut pas être vide.");
    }
}

package modele;

public class admin extends AbstractUser{
    public admin(int id,String userName,String email,String motDepass){
        super(id, userName,email,motDepass);
    }
    @Override
    protected Livre rechercheLivre() {return null;}
    @Override
    protected void changeMdpClient() {}
    public Client chercherUtilisateur() { return null; }
    public void ajouterLivre() { /* will call LivreService */ }
    public void supprimerLivre() { /* will call LivreService */ }
    public void blockerClient() { /* will call UserService */ }
    public void changerMdpAdmin() { /* to implement */ }
}

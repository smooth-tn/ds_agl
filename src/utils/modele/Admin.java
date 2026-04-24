package src.modele;

public class Admin extends AbstractUser{
    public Admin(int id,String userName,String email){
        super(id, userName,email);
    }
    @Override
    protected Livre rechercheLivre() {return null;}
    @Override
    protected void changeMdpClient() {}
    public Client chercherUtilisateur() { return null; }
    public void ajouterLivre() { /*  nchalah*/ }
    public void supprimerLivre() { /* nchalah */ }
    public void blockerClient() { /* nchalah */ }
    public void changerMdpAdmin() { /* nchalah */ }
}

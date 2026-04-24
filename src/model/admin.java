package model;

public class admin extends AbstractUser{
    public admin(int id,String userName,String email,String motDepass){
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

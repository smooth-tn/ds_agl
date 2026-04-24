package model;

import java.util.ArrayList;

public class Client extends AbstractUser{
    private boolean isBlocked=false;
    private ArrayList<Livre> panier;
    private int empruntCont=0;

    public Client(int id,String userName,String email,String motDepass){
        super(id, userName,email);
        this.panier=new ArrayList<>();
    }
    public void setBlocked() {isBlocked = true;}
    public ArrayList<Livre> getPanier() { return panier; }
    public int getEmpruntCont() { return empruntCont;}
    @Override
    public Livre rechercheLivre(){
        System.out.println("livre");
        return new Livre(0,"","","",false,0,0,0);
    }
    @Override
    public  void changeMdpClient(){
        System.out.println("mdp changer");
    }
}

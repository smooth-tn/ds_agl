package model;

public class Livre {
    private int id;
    private Date dateAjout;
    private String Titre;
    private String auteur;
    private String categorie;
    private boolean isDispo;

    public Livre(String Titre,String auteur,String categorie){
        this.Titre=Titre;
        this.auteur=auteur;
        this.categorie=categorie;
    }

    public Livre(int id,String Titre,String auteur,String categorie,boolean isDispo,int jour,int month,int year){
        this.id=id;
        this.Titre=Titre;
        this.auteur=auteur;
        this.categorie=categorie;
        this.isDispo=isDispo;
        dateAjout=new Date(jour,month,year);
    }

    public int getId() {return id;}
    public String getTitre() {return Titre;}
    public String getAuteur() {return auteur;}
    public String getDateAjout() {return dateAjout.toString();}
    public String getCategorie() {return categorie;}
    public boolean getIsDispo(){return isDispo;}


}

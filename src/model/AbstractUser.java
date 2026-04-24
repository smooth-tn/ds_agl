package modele;

public abstract class AbstractUser {
    protected  int id;
    protected String userName;

    protected String email;
    public AbstractUser(int id,String userName,String email,String ){
        this.id=id;
        this.userName=userName;
        this.email=email;
    }

    public int getId() {return id;}
    public String getEmail() {return email;}
    public String getUserName() {return userName;}

    public void setEmail(String email) {this.email = email;}
    public void setId(int id) {this.id = id;}
    public void setUserName(String userName) {this.userName = userName;}

    protected abstract Livre rechercheLivre();
    protected abstract void changeMdpClient();


}

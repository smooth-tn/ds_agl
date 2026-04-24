package model;

public class Compte {
    private String email;
    private String motDePass;
    private String userName;
    private boolean isClient=false;

    public void setMotDePass(String motDePass) {
        if(!motDePass.isEmpty()) this.motDePass = motDePass;
        else throw new NullPasswordException();
    }
    public void setEmail(String email) {
        if(EmailValidation.isValidEmail(this.email)) this.email = email;
        else throw new EmptyEmailException();
    }
    public void setClient() {isClient = true;}
    public void setUserName(String name){this.userName=name;}
    
    public String getUserName(){return userName;}
    public boolean getIsClient(){return isClient;}
    public String getEmail() {return email;}
    public String getMotDePass() {return motDePass;}
}

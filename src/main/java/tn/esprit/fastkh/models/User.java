package tn.esprit.fastkh.models;


public class User {
    private int id, phoneNum;
    private String nom;
    private String prenom;
    private String email;
    private String password;
    private String confirmPassword;
    private String statusCompte;

    public User(){

    }
    public User(int id, String nom, String prenom, String email, String password, String confirmPassword,int phoneNum ,String statusCompte) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.phoneNum = phoneNum;
        this.statusCompte = statusCompte;
    }

    public User(String nom, String prenom, String email, String password, String confirmPassword,int phoneNum, String statusCompte) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.phoneNum = phoneNum;
        this.statusCompte = statusCompte;
    }

    public User(int id, String statusCompte) {
        this.id = id;
        this.statusCompte = statusCompte;
    }

    public User(int id, String nom, String prenom, String email, String telephone, String statusCompte) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.phoneNum = Integer.parseInt(telephone);
        this.statusCompte = statusCompte;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public int getPhoneNum() {return phoneNum;}

    public void setPhoneNum(int phoneNum) {this.phoneNum = phoneNum;}

    public String getStatusCompte() {
        return statusCompte;
    }

    public void setStatusCompte(String statusCompte) {
        this.statusCompte = statusCompte;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", confirmPassword='" + confirmPassword + '\'' +
                ", phoneNum=" + phoneNum + '\'' +
                ", statusCompte='" + statusCompte + '\'' +
                '}';
    }
}

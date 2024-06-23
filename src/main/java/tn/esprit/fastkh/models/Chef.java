package tn.esprit.fastkh.models;

public class Chef extends User{


 private int idChef ;

 public Chef(){}

 public Chef(int idChef) {
  this.idChef = idChef;
 }


 public int getIdChef() {
  return idChef;
 }

 public void setIdChef(int idChef) {
  this.idChef = idChef;
 }

 @Override
 public String toString() {
  return "Chef{" +
          "idChef=" + idChef +
          '}';
 }
}

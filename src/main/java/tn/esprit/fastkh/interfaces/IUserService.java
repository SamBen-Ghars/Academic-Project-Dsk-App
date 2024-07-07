package tn.esprit.fastkh.interfaces;

import tn.esprit.fastkh.models.User;

import java.sql.SQLException;
import java.util.List;



public interface IUserService<T>{

    //insertion des donner d'un chef juste aprés l'inscription


    void ajouter(T t) throws SQLException;

    void displayName(User user);

    void ajouteChef1(User user) throws SQLException;

    void modifier(T t) throws SQLException;
    void supprimer(int id) throws SQLException;
    List<T> recuperer() throws SQLException;


}

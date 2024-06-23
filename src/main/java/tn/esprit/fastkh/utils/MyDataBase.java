package tn.esprit.fastkh.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyDataBase {


    //DB PARAM
    static final String URL ="jdbc:mysql://localhost:3306/fast-hk";
    static final String USER ="root";
    static final String PASSWORD ="";

    //var
    private Connection cnx;
    //1
    static MyDataBase instance;

    //const
    //2
    public MyDataBase(){
        try {
            cnx = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connexion reusite ");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }


    public Connection getCnx() {
        return cnx;
    }

    //3
    public static MyDataBase getInstance() {
        if(instance == null)
            instance = new MyDataBase();

        return instance;
    }
}

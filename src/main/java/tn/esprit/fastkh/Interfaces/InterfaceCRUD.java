package tn.esprit.fastkh.Interfaces;

import java.sql.SQLException;
import java.util.List;

public interface InterfaceCRUD<S> {


    //CRUD
    public void add(S s);
    public void delete(S s);
    public void update(S s) throws SQLException;
    public List<S> getAll();
}

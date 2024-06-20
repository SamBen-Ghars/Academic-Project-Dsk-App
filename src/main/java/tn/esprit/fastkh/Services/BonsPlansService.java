package tn.esprit.fastkh.Services;

import tn.esprit.fastkh.Interfaces.InterfaceCRUD;
import tn.esprit.fastkh.Models.BonsPlans;
import tn.esprit.fastkh.Utils.MyDataBase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BonsPlansService implements InterfaceCRUD<BonsPlans> {

    Connection cnx = MyDataBase.getInstance().getCnx();


    @Override
    public void add(BonsPlans bon) {

        String req = "INSERT INTO bonsplans (Title, Description, Adresse ,image) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setString(1, bon.getTitle());
            pst.setString(2, bon.getDescription());
            pst.setString(3, bon.getAdresse());
            pst.setBytes(4, bon.getImage());
            pst.executeUpdate();
            System.out.println("Bons plans ajouté avec succès");
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de l'ajout du bons plans", e);
        }
    }

    @Override
    public void delete(BonsPlans bon) {
        String req = "DELETE FROM bonsplans WHERE id = ?";
        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, bon.getId());
            int rowsAffected = pst.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Bons plans supprimé avec succès");
            } else {
                System.out.println("Aucun bons plans trouvé avec l'ID spécifié");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la suppression du bons plans", e);
        }
    }

    @Override
    public void update(BonsPlans bon) throws SQLException {
        String req = "UPDATE bonsplans SET title = ?, description = ?, adresse = ? , image = ?  WHERE id = ?";
        PreparedStatement pst = cnx.prepareStatement(req);
        pst.setString(1, bon.getTitle());
        pst.setString(2, bon.getDescription());
        pst.setString(3, bon.getAdresse());
        pst.setInt(4, bon.getId());
        pst.setBytes(5, bon.getImage());

        pst.executeUpdate();
    }

    @Override
    public List<BonsPlans> getAll() {
        List<BonsPlans> bonsPlans = new ArrayList<>();
        String req = "SELECT * FROM `bonsplans` ";

        try {
            //Statement st = cnx.createStatement();
            PreparedStatement st = cnx.prepareStatement(req);
            ResultSet res = st.executeQuery(req);

            while (res.next()) {
                BonsPlans bp = new BonsPlans();
                bp.setId(res.getInt("id"));
                bp.setTitle(res.getString("Title"));
                bp.setDescription(res.getString("Description"));
                bp.setAdresse(res.getString("Adresse"));
                bp.setImage(res.getBytes("image"));
                bonsPlans.add(bp);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la récupération des bons plans", e);
        }
        return bonsPlans;
    }
}

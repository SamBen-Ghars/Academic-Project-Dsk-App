package tn.esprit.fastkh.services;

import tn.esprit.fastkh.interfaces.InterfaceCRUD;
import tn.esprit.fastkh.models.BonsPlans;
import tn.esprit.fastkh.utils.MyDataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BonsPlansService implements InterfaceCRUD<BonsPlans> {

    private List<BonsPlans> bonsPlansList = new ArrayList<>();

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
        List<BonsPlans> bonsPlansList = new ArrayList<>();
        String req = "SELECT * FROM bonsplans";
        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            ResultSet res = pst.executeQuery();
            while (res.next()) {
                BonsPlans bp = new BonsPlans(
                        res.getInt("id"),
                        res.getString("Title"),
                        res.getString("Description"),
                        res.getString("Adresse"),
                        res.getBytes("image")
                );
                bonsPlansList.add(bp);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la récupération des bons plans", e);
        }
        return bonsPlansList;
    }






    // Method to search BonsPlans by ID, title, description, and adresse
    public List<BonsPlans> searchBonsPlans(String searchText) {
        List<BonsPlans> results = new ArrayList<>();
        String sql = "SELECT * FROM bonsplans WHERE id = ? OR LOWER(title) LIKE ? OR LOWER(description) LIKE ? OR LOWER(adresse) LIKE ?";

        try (
             PreparedStatement pstmt = cnx.prepareStatement(sql)) {

            // Try to parse the search text as an integer for ID search
            int id = -1;
            try {
                id = Integer.parseInt(searchText);
            } catch (NumberFormatException e) {
                // searchText is not a valid integer, so we skip ID search
            }

            pstmt.setInt(1, id);
            pstmt.setString(2, "%" + searchText.toLowerCase() + "%");
            pstmt.setString(3, "%" + searchText.toLowerCase() + "%");
            pstmt.setString(4, "%" + searchText.toLowerCase() + "%");

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                BonsPlans bp = new BonsPlans();
                bp.setId(rs.getInt("id"));
                bp.setTitle(rs.getString("title"));
                bp.setDescription(rs.getString("description"));
                bp.setAdresse(rs.getString("adresse"));
                bp.setImage(rs.getBytes("image"));
                results.add(bp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return results;
    }


    public List<BonsPlans> fetchRecentlyAddedBonsPlans() {
        List<BonsPlans> ls = new ArrayList<>();
        String sql = "SELECT * FROM bonsplans ORDER BY id DESC LIMIT 10"; // Fetch last 10 added BonsPlans

        try (
             PreparedStatement pstmt = cnx.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                BonsPlans plans = new BonsPlans();
                plans.setId(rs.getInt("id"));
                plans.setTitle(rs.getString("title"));
                plans.setDescription(rs.getString("description"));
                plans.setAdresse(rs.getString("adresse"));
                plans.setImage(rs.getBytes("image"));
                ls.add(plans);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ls;
    }


}

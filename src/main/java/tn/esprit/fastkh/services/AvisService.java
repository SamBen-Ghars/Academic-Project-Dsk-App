package tn.esprit.fastkh.services;

import tn.esprit.fastkh.interfaces.InterfaceCRUD;
import tn.esprit.fastkh.models.Avis;
import tn.esprit.fastkh.utils.MyDataBase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AvisService implements InterfaceCRUD<Avis> {

    Connection cnx = MyDataBase.getInstance().getCnx();


    @Override
    public void add(Avis avis) {
        String query = "INSERT INTO avis (userid, bonsPlanid, notes, commentaire) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = cnx.prepareStatement(query)) {
            statement.setInt(1, avis.getUserId());
            statement.setInt(2, avis.getBonsPlanId());
            statement.setInt(3, avis.getNotes());
            statement.setString(4, avis.getCommentaire());
            statement.executeUpdate();
        } catch (SQLException e) {
            if (e instanceof SQLIntegrityConstraintViolationException) {
                // Handle specific constraint violation
                System.err.println("Foreign key constraint violated. User does not exist.");
            } else {
                e.printStackTrace();
            }
            // Handle other exceptions or log appropriately
        }
    }



    @Override
    public void delete(Avis avis) {
        String query = "DELETE FROM avis WHERE id = ?";
        try ( PreparedStatement statement = cnx.prepareStatement(query)) {
            statement.setInt(1, avis.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Avis avis) throws SQLException {
        String query = "UPDATE avis SET notes = ?, commentaire = ? WHERE id = ?";
        try ( PreparedStatement statement = cnx.prepareStatement(query)) {
            statement.setInt(1, avis.getNotes());
            statement.setString(2, avis.getCommentaire());
            statement.setInt(3, avis.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Avis> getAll() {
        List<Avis> avisList = new ArrayList<>();
        String query = "SELECT * FROM avis";
        try ( PreparedStatement statement = cnx.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Avis avis = new Avis(
                        resultSet.getInt("id"),
                        resultSet.getInt("userId"),
                        resultSet.getInt("bonsPlanId"),
                        resultSet.getInt("notes"),
                        resultSet.getString("commentaire")
                );
                avisList.add(avis);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return avisList;
    }

    public List<Avis> getAvisByBonsPlanId(int bonsPlanId) {
        List<Avis> avisList = new ArrayList<>();
        String query = "SELECT * FROM avis WHERE bonsPlanId = ?";
        try ( PreparedStatement statement = cnx.prepareStatement(query)) {
            statement.setInt(1, bonsPlanId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Avis avis = new Avis(
                        resultSet.getInt("id"),
                        resultSet.getInt("userId"),
                        resultSet.getInt("bonsPlanId"),
                        resultSet.getInt("notes"),
                        resultSet.getString("commentaire")
                );
                avisList.add(avis);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return avisList;
    }


    public double getAverageRatingByBonsPlanId(int bonsPlanId) {
        return 0;
    }
}

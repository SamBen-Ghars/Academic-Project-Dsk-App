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

        String req ="INSERT INTO `bonsplans`( `titre`, `discription`, `addresse`) VALUES ('"+bon.getTitre()+"','"+bon.getDiscription()+"','"+bon.getAddresse()+"')";

        try {
            Statement st =cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Bons plans ajouter avec succees ");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(int id) {


        String req = "DELETE FROM bonsplans WHERE id = ?";

        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, id); // Assurez-vous que getId() retourne un int
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
    public void update(BonsPlans Bon) throws SQLException {

        String req ="UPDATE `bonsplans` SET titre= ?, discription = ?, addresse = ? WHERE id=?";
        PreparedStatement pst = cnx.prepareStatement(req);

       pst.setString(1, Bon.getTitre());
       pst.setString(2,Bon.getDiscription());
       pst.setString(3,Bon.getAddresse());
       pst.executeUpdate(req);







    }

    @Override
    public List<BonsPlans> getAll() {
        List<BonsPlans> bonsPlans = new ArrayList<>();
        String req = "SELECT * FROM bonsplans";
        try {
            Statement st = cnx.createStatement();
            ResultSet res = st.executeQuery(req);
            while (res.next()){
                BonsPlans Bp =new BonsPlans();
                Bp.setId(res.getInt("id"));
                Bp.setTitre(((ResultSet) res).getString(2));
                Bp.setDiscription(res.getString(3));
                Bp.setAddresse(res.getString(4));

                bonsPlans.add(Bp);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return bonsPlans ;
    }


}

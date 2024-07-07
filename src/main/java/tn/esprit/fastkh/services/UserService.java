package tn.esprit.fastkh.services;


import org.mindrot.jbcrypt.BCrypt;
import tn.esprit.fastkh.interfaces.IUserService;
import tn.esprit.fastkh.models.User;
import tn.esprit.fastkh.utils.MyDataBase;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class UserService implements IUserService<User> {

    private Connection connection;

    public List<User> getChefsDemands() {
        List<User> users = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT id, nom, prenom, email, phoneNum, status FROM chefdemands");

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nom = resultSet.getString("nom");
                String prenom = resultSet.getString("prenom");
                String email = resultSet.getString("email");
                String phoneNum = resultSet.getString("phoneNum"); // Change to phoneNum
                String status = resultSet.getString("status"); // Change to statusCompte

                System.out.println("User: " + id + ", " + nom + ", " + prenom + ", " + email + ", " + phoneNum + ", " + status); // Debug statement

                User user = new User(id, nom, prenom, email, phoneNum, status); // Change constructor call
                users.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT id, nom, prenom, email, phoneNum, statusCompte FROM user");

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nom = resultSet.getString("nom");
                String prenom = resultSet.getString("prenom");
                String email = resultSet.getString("email");
                String phoneNum = resultSet.getString("phoneNum"); // Change to phoneNum
                String status = resultSet.getString("statusCompte"); // Change to statusCompte

                System.out.println("User: " + id + ", " + nom + ", " + prenom + ", " + email + ", " + phoneNum + ", " + status); // Debug statement

                User user = new User(id, nom, prenom, email, phoneNum, status); // Change constructor call
                users.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }

    public User findByEmail(String email) {
        User user = null;
        String queryLoginUser = "SELECT l.id, l.idUser, l.email, l.password, l.phoneNum, l.statusCompte, u.nom, u.prenom " +
                "FROM loginuser l " +
                "INNER JOIN user u ON l.idUser = u.id " +
                "WHERE l.email = ?";

        String queryChefDemands = "SELECT * FROM chefdemands WHERE email = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(queryLoginUser)) {
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getInt("id"));
                user.setId(resultSet.getInt("idUser")); // Assuming idUser is the correct column name
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                user.setPhoneNum(resultSet.getInt("phoneNum"));
                user.setStatusCompte(resultSet.getString("statusCompte"));
                user.setNom(resultSet.getString("nom")); // Set the nom from user table
                user.setPrenom(resultSet.getString("prenom"));
                // Set other user attributes as needed
                return user;
            } else {
                // If user not found in loginuser, check in chefdemands
                try (PreparedStatement chefDemandsStatement = connection.prepareStatement(queryChefDemands)) {
                    chefDemandsStatement.setString(1, email);
                    ResultSet chefDemandsResultSet = chefDemandsStatement.executeQuery();

                    if (chefDemandsResultSet.next()) {
                        // Create a new User object if found in chefdemands
                        user = new User();
                        user.setEmail(email); // Assuming only email is needed from chefdemands
                        return user;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null; // Return null if user not found in either table
    }

    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        String query = "SELECT * FROM user";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setNom(resultSet.getString("nom"));
                user.setPrenom(resultSet.getString("prenom"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public User findById(int userId) {
        String query = "SELECT nom, prenom FROM user WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                User user = new User();
                user.setNom(resultSet.getString("nom"));
                user.setPrenom(resultSet.getString("prenom"));

                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public void displayName(User user) {
        boolean chefNotYet = false;
        boolean apprenti = false;
        ;
        // Query to fetch user details based on email
        String query = "SELECT * FROM loginUser WHERE email = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            // Set the email parameter in the query
            preparedStatement.setString(1, user.getEmail());

            // Execute the query
            ResultSet resultSet = preparedStatement.executeQuery();

            // Check if a user is found
            if (resultSet.next()) {
                // Update the User object with the details from the database
                user.setId(resultSet.getInt("id"));
                user.setNom(resultSet.getString("nom"));
                user.setPrenom(resultSet.getString("prenom"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                // Set other user attributes as needed
            } else {
                // Handle the case where no user is found for the given email
                System.out.println("No user found with the email: " + user.getEmail());
            }
        } catch (SQLException e) {
            // Handle SQL exceptions
            e.printStackTrace();
        }

    }


    //insertion des donner d'un chef juste aprés l'inscription
    @Override
    public void ajouteChef1(User user) throws SQLException {
        String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());

        String req = "INSERT INTO chefdemands (nom, prenom, email, password, phoneNum, Status) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement st = connection.prepareStatement(req)) {
            st.setString(1, user.getNom());
            st.setString(2, user.getPrenom());
            st.setString(3, user.getEmail());
            st.setString(4, hashedPassword);
            st.setInt(5, user.getPhoneNum());
            st.setString(6, user.getStatusCompte());
            st.executeUpdate();
            System.out.println("Chef inserted for email: " + user.getEmail());
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Error during chef insertion: " + e.getMessage());
        }
    }

    public UserService(){
        connection = MyDataBase.getInstance().getCnx();
    }
    @Override
    public void ajouter(User user) throws SQLException {
        String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
        String hashedConfirmPassword = BCrypt.hashpw(user.getConfirmPassword(), BCrypt.gensalt());

        // Check the user's status and call ajouteChef1 if not apprenti
        if (!user.getStatusCompte().equals("apprenti")) {
            ajouteChef1(user);
        }else {
            String req = "INSERT INTO user (nom, prenom, email, password, confirmPassword,phoneNum, statusCompte) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement st = connection.prepareStatement(req, Statement.RETURN_GENERATED_KEYS);
            st.setString(1, user.getNom());
            st.setString(2, user.getPrenom());
            st.setString(3, user.getEmail());
            st.setString(4, hashedPassword);
            st.setString(5, hashedConfirmPassword);
            st.setInt(6, user.getPhoneNum());
            st.setString(7, user.getStatusCompte());
            st.executeUpdate();

            ResultSet generatedKeys = st.getGeneratedKeys();
            int userId = 0;
            if (generatedKeys.next()) {
                userId = generatedKeys.getInt(1);
            } else {
                throw new SQLException("Creating user failed, no ID obtained.");
            }

            System.out.println("User inserted with ID: " + userId);
            String req2 = "INSERT INTO loginUser (idUser, email, password, confirmPassword, phoneNum, statusCompte) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement st2 = connection.prepareStatement(req2);
            st2.setInt(1, userId); // idUser column in loginUser table
            st2.setString(2, user.getEmail()); // email column
            st2.setString(3, hashedPassword); // password column
            st2.setString(4, hashedConfirmPassword); // confirmPassword column
            st2.setInt(5, user.getPhoneNum()); // phoneNum column
            st2.setString(6, user.getStatusCompte()); // statusCompte column
            st2.executeUpdate();


        }
    }

    //    pour le compte du chef l'admin peut le dégradé en compte d'apprenti en cas de besoin
    @Override
    public void modifier(User user) throws SQLException {
        String req = "UPDATE user SET statusCompte=? WHERE id=?" ;
        PreparedStatement ps = connection.prepareStatement(req);
        ps.setString(1, user.getStatusCompte());
        ps.setInt(2, user.getId());
        ps.executeUpdate();

        String getIdQuery = "SELECT id FROM user WHERE id = '"+user.getId()+"'";
        Statement st3 = connection.createStatement();
        ResultSet rs = st3.executeQuery(getIdQuery);
        int idUser = 0;
        if (rs.next()) {
            idUser = rs.getInt("id");
        }

        String req1 = "UPDATE loginUser SET statusCompte=? WHERE idUser=?";
        PreparedStatement ps1 = connection.prepareStatement(req1);
        ps1.setString(1, user.getStatusCompte());
        ps1.setInt(2, idUser);
        ps1.executeUpdate();

    }

    @Override
    public void supprimer(int id) throws SQLException {
        String req = "DELETE FROM user WHERE id = ?";
        PreparedStatement ps = connection.prepareStatement(req);
        ps.setInt(1,id);
        ps.executeUpdate();
    }

    @Override
    public List<User> recuperer() throws SQLException {
        List<User> users = new ArrayList<>();
        String req = "SELECT * FROM user";
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(req);

        while (rs.next()){
            User user = new User();
            user.setId(rs.getInt("id"));
            user.setId(rs.getInt("idUser"));
            user.setEmail(rs.getString("email"));
            user.setPassword(rs.getString("password"));
            user.setStatusCompte(rs.getString("statusCompte"));

            users.add(user);
        }
        return users;
    }


    public void deleteUser(int id) {
        // Delete user from the database
        String query = "DELETE FROM user WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // Code to delete the user
        System.out.println("Deleting user: " + id);
    }

    public void updateUser(User user) {
        String query = "UPDATE user SET nom = ?, prenom = ?, email = ?, phoneNum = ?, statusCompte = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, user.getNom());
            statement.setString(2, user.getPrenom());
            statement.setString(3, user.getEmail());
            statement.setString(4, String.valueOf(user.getPhoneNum()));
            statement.setString(5, user.getStatusCompte());
            statement.setInt(6, user.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateUserStatus(int userId, String newStatus) {
        String query = "UPDATE chefdemands SET status = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, newStatus);
            statement.setInt(2, userId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteChef(int userId) {
        String deleteQuery = "DELETE FROM chefdemands WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(deleteQuery)) {
            statement.setInt(1, userId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Map<String, String> getPassConfPass(int userId) {
        String query = "SELECT password FROM chefdemands WHERE id = ?";
        Map<String, String> passMap = new HashMap<>();

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                passMap.put("password", resultSet.getString("password"));
//                passMap.put("confirmPassword", resultSet.getString("confirmPassword"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return passMap;
    }


    public void addChefVerifier(User user) {
        try {

            Map<String, String> passMap = getPassConfPass(user.getId());
            String originalPassword = passMap.get("password");
//            String originalConfirmPassword = passMap.get("confirmPassword");

            // Update user status to 'chefVerifier' in chefdemands table
            updateUserStatus(user.getId(), "chefVerifier");

            String req = "INSERT INTO user (nom, prenom, email, password, confirmPassword, phoneNum, statusCompte) VALUES (?, ?, ?, ?, ?, ?, " + "'chefVerifier')";
            PreparedStatement st = connection.prepareStatement(req, Statement.RETURN_GENERATED_KEYS);
            st.setString(1, user.getNom());
            st.setString(2, user.getPrenom());
            st.setString(3, user.getEmail());
            st.setString(4, originalPassword);
            st.setString(5, originalPassword);
            st.setInt(6, user.getPhoneNum());
//            st.setString(7, user.getStatusCompte());
            st.executeUpdate();

            ResultSet generatedKeys = st.getGeneratedKeys();

            int userId = 0;
            if (generatedKeys.next()) {
                userId = generatedKeys.getInt(1);
            } else {
                throw new SQLException("Creating user failed, no ID obtained.");
            }

            System.out.println("User inserted with ID: " + userId);

            // Insert user into loginUser table
            String req2 = "INSERT INTO loginUser (idUser, email, password, confirmPassword, phoneNum, statusCompte) VALUES (?, ?, ?, ?, ?, " + "'chefVerifier')";
            PreparedStatement st2 = connection.prepareStatement(req2);
            st2.setInt(1, userId); // idUser column in loginUser table
            st2.setString(2, user.getEmail()); // email column
            st2.setString(3, originalPassword); // password column
            st2.setString(4, originalPassword); // confirmPassword column
            st2.setInt(5, user.getPhoneNum()); // phoneNum column
//            st2.setString(6, user.getStatusCompte()); // statusCompte column
            st2.executeUpdate();

            // Delete user from chefdemands table
            deleteChef(user.getId());

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    public boolean updatePassword(String email, String newPassword) {
        String hashedPassword = BCrypt.hashpw(newPassword, BCrypt.gensalt());
        String query = "UPDATE loginuser SET password = ?, confirmPassword = ? WHERE email = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, hashedPassword);
            preparedStatement.setString(2, hashedPassword); // Setting confirmPassword to the same hashed password
            preparedStatement.setString(3, email);

            int rowsUpdated = preparedStatement.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }



}

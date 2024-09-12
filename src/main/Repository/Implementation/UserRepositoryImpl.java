package main.Repository.Implementation;

import main.Repository.UserRepositoryInterface;
import main.Util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepositoryImpl implements UserRepositoryInterface {

    @Override
    public boolean checkUser(String cnie){
        boolean userExists = false;

        try {
            // Get a connection from the singleton
            Connection connection = DBConnection.getInstance().getConnection();

            // SQL query to check if a user with the given CNIE exists
            String sql = "SELECT * FROM \"User\" WHERE cnie = ?";


            // Prepare the statement with the provided CNIE
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, cnie);

            // Execute the query
            ResultSet resultSet = statement.executeQuery();

            // If a row is returned, the user exists
            if (resultSet.next()) {
                userExists = true;
            }

            // Close the result set and statement
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userExists;
    }

        @Override
        public void createUser(String lname, String fname, String cnie, String password) {
            String sql = "INSERT INTO public.\"User\" (\"LastName\", \"FrstName\", \"cnie\", \"password\") VALUES (?, ?, ?, ?)";

            try (Connection connection = DBConnection.getInstance().getConnection();
                 PreparedStatement statement = connection.prepareStatement(sql)) {

                statement.setString(1, lname);
                statement.setString(2, fname);
                statement.setString(3, cnie);
                statement.setString(4, password);

                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
                // Handle exception (e.g., log it or throw a custom exception)
            }
        }
    }



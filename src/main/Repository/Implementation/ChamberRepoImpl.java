package main.Repository.Implementation;

import main.Entity.Chambre;
import main.Entity.Hotel;
import main.Repository.ChamberRepoInterface;
import main.Util.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ChamberRepoImpl implements ChamberRepoInterface {

    @Override
    public void createChamber(Chambre chambre) {
        String sql = "INSERT INTO public.\"Room\" (hotel, type, quantity, prix) VALUES (?, ?, ?, ?)";
        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, chambre.getHotel().getId());
            statement.setString(2, chambre.getType());
            statement.setInt(3, chambre.getQuantity());
            statement.setDouble(4, chambre.getPrix());

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Chambre findChamberById(int id) {
        String sql = "SELECT ro.id, ro.type, ro.quantity, ro.prix, h.id AS hotel_id, h.\"Name\", h.location, h.\"Rate\" " +
                "FROM public.\"Room\" ro " +
                "JOIN public.\"Hotels\" h ON ro.hotel = h.id WHERE ro.id = ?";
        Chambre chambre = null;

        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                Hotel hotel = new Hotel(resultSet.getString("Name"), resultSet.getString("location"));
                hotel.setId(resultSet.getInt("hotel_id"));
                hotel.setRate(resultSet.getDouble("Rate"));

                chambre = new Chambre(hotel,
                        resultSet.getString("type"),
                        resultSet.getInt("quantity"),
                        resultSet.getDouble("prix"));
                chambre.setId(resultSet.getInt("id"));
            }

            resultSet.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return chambre;
    }

    @Override
    public List<Chambre> findChambersByHotelId(Hotel hotel) {
        String sql = "SELECT ro.id, ro.type, ro.quantity, ro.prix FROM public.\"Room\" ro WHERE ro.hotel = ?";
        List<Chambre> chambres = new ArrayList<>();

        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, hotel.getId());
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Chambre chambre = new Chambre(hotel,resultSet.getString("type"),resultSet.getInt("quantity"),resultSet.getDouble("prix"));
                chambre.setId(resultSet.getInt("id"));
                chambres.add(chambre);
            }

            resultSet.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return chambres;
    }

    @Override
    public void updateChamber(Chambre chambre) {
        String sql = "UPDATE public.\"Room\" SET type = ?, quantity = ?, prix = ? WHERE id = ?";
        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, chambre.getType());
            statement.setInt(2, chambre.getQuantity());
            statement.setDouble(3, chambre.getPrix());
            statement.setInt(4, chambre.getId());

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteChamber(int id) {
        String sql = "DELETE FROM public.\"Room\" WHERE id = ?";
        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

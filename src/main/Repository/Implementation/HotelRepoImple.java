package main.Repository.Implementation;

import main.Entity.Hotel;
import main.Repository.HotelRepoInterface;
import main.Util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HotelRepoImple implements HotelRepoInterface {

    @Override
    public void createHotel(Hotel hotel) {
        String sql = "INSERT INTO public.\"Hotels\" (\"Name\", location, \"Rate\") VALUES (?, ?, ?)";
        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, hotel.getName());
            statement.setString(2, hotel.getLocation());
            statement.setDouble(3, hotel.getRate());

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Hotel findHotelById(int id) {
        String sql = "SELECT id, \"Name\", location, \"Rate\" FROM public.\"Hotels\" WHERE id = ?";
        Hotel hotel = null;

        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                hotel = new Hotel(resultSet.getString("Name"), resultSet.getString("location"));
                hotel.setId(resultSet.getInt("id"));
                hotel.setRate(resultSet.getDouble("Rate"));
            }

            resultSet.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return hotel;
    }

    @Override
    public List<Hotel> findAllHotels() {
        String sql = "SELECT id, \"Name\", location, \"Rate\" FROM public.\"Hotels\"";
        List<Hotel> hotels = new ArrayList<>();

        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Hotel hotel = new Hotel(resultSet.getString("Name"), resultSet.getString("location"));
                hotel.setId(resultSet.getInt("id"));
                hotel.setRate(resultSet.getDouble("Rate"));
                hotels.add(hotel);
            }

            resultSet.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return hotels;
    }

    @Override
    public void updateHotel(Hotel hotel) {
        String sql = "UPDATE public.\"Hotels\" SET \"Name\" = ?, location = ?, \"Rate\" = ? WHERE id = ?";
        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, hotel.getName());
            statement.setString(2, hotel.getLocation());
            statement.setDouble(3, hotel.getRate());
            statement.setInt(4, hotel.getId());

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteHotel(int id) {
        String sql = "DELETE FROM public.\"Hotels\" WHERE id = ?";
        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

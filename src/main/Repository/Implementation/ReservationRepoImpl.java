package main.Repository.Implementation;

import main.Entity.Chambre;
import main.Entity.Customer;
import main.Entity.Hotel;
import main.Entity.Reservations;
import main.Repository.ReservationRepoInterface;
import main.Util.DBConnection;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ReservationRepoImpl implements ReservationRepoInterface {

    @Override
    public void createReservation(Reservations reservation) {
        String sql = "INSERT INTO reservations (id,User,Room ,checkin, checkout) VALUES (\'NULL\',?, ?, ?,?)";
        LocalDateTime checkinDateTime = reservation.getCheckin().atStartOfDay();
        LocalDateTime checkoutDateTime = reservation.getCheckout().atStartOfDay();

        Timestamp checkinTimestamp = Timestamp.valueOf(checkinDateTime);
        Timestamp checkoutTimestamp = Timestamp.valueOf(checkoutDateTime);


        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, reservation.getUser().getCnie());
            statement.setInt(2, reservation.getRoom().getId());
            statement.setTimestamp(3, checkinTimestamp);
            statement.setTimestamp(4, checkoutTimestamp);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public List<Reservations> findReservationsByCnie(String cnie) {
        String sql = "SELECT r.*, u.*, ro.*, h.* FROM public.\"Reservations\" r JOIN public.\"User\" u ON u.cnie = r.\"User\" JOIN public.\"Room\" ro ON ro.id = r.\"Room\" JOIN public.\"Hotels\" h ON h.id = ro.hotel;";
        List<Reservations> reservationsList = new ArrayList<>();

        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, cnie);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Hotel hotel=new Hotel(resultSet.getString("name"),resultSet.getString("location"));
                hotel.setId(resultSet.getInt("h.id"));
                Customer customer= new Customer(resultSet.getString("cnie"),resultSet.getString("FrstName"),resultSet.getString("LastName"));
                Chambre room=new Chambre()
                Reservations reservation = new Reservations(customer,);
                reservationsList.add(reservation);
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return reservationsList;
    }

    @Override
    public void updateReservation(Reservations reservation) {
        String sql = "UPDATE reservations SET checkin = ?, checkout = ?,room = ? WHERE id = ?";
        LocalDateTime checkinDateTime = reservation.getCheckin().atStartOfDay();
        LocalDateTime checkoutDateTime = reservation.getCheckout().atStartOfDay();

        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setTimestamp(1, Timestamp.valueOf(checkinDateTime));
            statement.setTimestamp(2, Timestamp.valueOf(checkoutDateTime));
            statement.setInt(3, reservation.getRoom().getId());
            statement.setInt(4, reservation.getId());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteReservation(Reservations reservation) {
        String sql = "DELETE FROM reservations WHERE id = ?";

        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1,reservation.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}

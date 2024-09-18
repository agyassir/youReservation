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
        String sql = "INSERT INTO \"Reservations\" (\"User\",\"Room\" ,checkin, checkout) VALUES (?, ?, ?,?);";
        Date checkinDateTime = Date.valueOf(reservation.getCheckin());
        Date checkoutDateTime = Date.valueOf(reservation.getCheckout());
System.out.println(reservation.getUser().getCnie());



        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, reservation.getUser().getCnie());
            statement.setInt(2, reservation.getRoom().getId());
            statement.setDate(3, checkinDateTime);
            statement.setDate(4, checkoutDateTime);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Reservations findReservationByid(int id) {
        String sql = "SELECT r.id AS reservation_id, r.\"Room\" AS room_id, r.checkin, r.checkout, " +
                "u.cnie, u.\"FirstName\", u.\"LastName\", " +
                "ro.id AS room_id, ro.type, ro.quantity, ro.prix, " +
                "h.id AS hotel_id, h.\"Name\" AS hotel_name, h.location, h.\"Rate\" " +
                "FROM public.\"Reservations\" r " +
                "JOIN public.\"User\" u ON u.cnie = r.\"User\" " +
                "JOIN public.\"Room\" ro ON ro.id = r.\"Room\" " +
                "JOIN public.\"Hotels\" h ON h.id = ro.hotel " +
                "WHERE r.id = ?;";

        Reservations reservation = null;

        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();


            if (resultSet.next()) {

                Hotel hotel = new Hotel(resultSet.getString("hotel_name"), resultSet.getString("location"));
                hotel.setId(resultSet.getInt("hotel_id"));
                hotel.setRate(resultSet.getDouble("Rate"));

                // Customer object
                Customer customer = new Customer(resultSet.getString("cnie"),
                        resultSet.getString("FirstName"),
                        resultSet.getString("LastName"));

                // Room object (Chambre)
                Chambre room = new Chambre(hotel,
                        resultSet.getString("type"),
                        resultSet.getInt("quantity"),
                        resultSet.getDouble("prix"));

                // Reservation object
                reservation = new Reservations(customer,
                        room,
                        resultSet.getDate("checkin").toLocalDate(),
                        resultSet.getDate("checkout").toLocalDate());
                reservation.setId(resultSet.getInt("reservation_id"));
            }

            resultSet.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return reservation;
    }


    @Override
    public List<Reservations> findReservationsByCnie(String cnie) {
        String sql = "SELECT r.id AS reservation_id, r.\"Room\" AS room_id, r.checkin, r.checkout, u.*, ro.* AS room_id, h.id AS hotel_id, h.location, h.\"Rate\", h.\"Name\" FROM public.\"Reservations\" r JOIN public.\"User\" u ON u.cnie = r.\"User\" JOIN public.\"Room\" ro ON ro.id = r.\"Room\" JOIN public.\"Hotels\" h ON h.id = ro.hotel WHERE u.cnie=?;";
        List<Reservations> reservationsList = new ArrayList<>();

        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, cnie);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Hotel hotel=new Hotel(resultSet.getString("name"),resultSet.getString("location"));
                hotel.setId(resultSet.getInt("hotel_id"));
                Customer customer= new Customer(resultSet.getString("cnie"),resultSet.getString("FrstName"),resultSet.getString("LastName"));
                Chambre room=new Chambre(hotel, resultSet.getString("type"), resultSet.getInt("quantity"),resultSet.getDouble("prix") );
                Reservations reservation = new Reservations(customer,room,resultSet.getDate("checkin").toLocalDate(),resultSet.getDate("checkout").toLocalDate());
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

    @Override
    public List<Reservations> findReservationsByRoom(Chambre room) {
        List<Reservations> reservations = new ArrayList<>();
        String sql = "SELECT r.id AS reservation_id, r.\"Room\" AS room_id, r.checkin, r.checkout, " +
                "u.cnie, u.\"FirstName\", u.\"LastName\", " +
                "ro.id AS room_id, ro.type, ro.quantity, ro.prix, " +
                "h.id AS hotel_id, h.\"Name\" AS hotel_name, h.location, h.\"Rate\" " +
                "FROM public.\"Reservations\" r " +
                "JOIN public.\"User\" u ON u.cnie = r.\"User\" " +
                "JOIN public.\"Room\" ro ON ro.id = r.\"Room\" " +
                "JOIN public.\"Hotels\" h ON h.id = ro.hotel " +
                "WHERE ro.id = ?;";

        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            // Set the room ID parameter in the query
            statement.setInt(1, room.getId());

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                // Create the user object
                Customer user = new Customer(resultSet.getString("cnie"),
                        resultSet.getString("FirstName"),
                        resultSet.getString("LastName"));

                // Create the hotel object
                Hotel hotel = new Hotel(resultSet.getString("hotel_name"),
                        resultSet.getString("location"));
                hotel.setId(resultSet.getInt("hotel_id"));

                // Create the room object
                Chambre chambre = new Chambre(hotel,
                        resultSet.getString("type"),
                        resultSet.getInt("quantity"),
                        resultSet.getDouble("prix"));
                chambre.setId(resultSet.getInt("room_id"));


                Reservations reservation = new Reservations(user, chambre,
                        resultSet.getDate("checkin").toLocalDate(),
                        resultSet.getDate("checkout").toLocalDate());
                reservation.setId(resultSet.getInt("reservation_id"));

                reservations.add(reservation);
            }

            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return reservations;
    }

    @Override
    public List<Reservations> AllResrvation() {
        List<Reservations> reservations = new ArrayList<>();
        String sql = "SELECT r.id AS reservation_id, r.\"Room\" AS room_id, r.checkin, r.checkout, " +
                "u.cnie, u.\"FirstName\", u.\"LastName\", " +
                "ro.id AS room_id, ro.type, ro.quantity, ro.prix, " +
                "h.id AS hotel_id, h.\"Name\" AS hotel_name, h.location, h.\"Rate\" " +
                "FROM public.\"Reservations\" r " +
                "JOIN public.\"User\" u ON u.cnie = r.\"User\" " +
                "JOIN public.\"Room\" ro ON ro.id = r.\"Room\" " +
                "JOIN public.\"Hotels\" h ON h.id = ro.hotel;";

        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                // Create user object
                Customer user = new Customer(resultSet.getString("cnie"),
                        resultSet.getString("FirstName"),
                        resultSet.getString("LastName"));

                Hotel hotel = new Hotel();
                hotel.setName(resultSet.getString("hotel_name"));
                hotel.setLocation(resultSet.getString("location"));
                hotel.setId(resultSet.getInt("hotel_id"));

                Chambre chambre = new Chambre(hotel,
                        resultSet.getString("type"),
                        resultSet.getInt("quantity"),
                        resultSet.getDouble("prix"));
                chambre.setId(resultSet.getInt("room_id"));

                Reservations reservation = new Reservations(user, chambre,
                        resultSet.getDate("checkin").toLocalDate(),
                        resultSet.getDate("checkout").toLocalDate());
                reservation.setId(resultSet.getInt("reservation_id"));

                reservations.add(reservation);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return reservations;
    }

}

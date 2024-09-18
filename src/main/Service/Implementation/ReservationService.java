package main.Service.Implementation;

import main.Entity.Chambre;
import main.Entity.Reservations;
import main.Repository.ReservationRepoInterface;

import java.time.LocalDate;
import java.util.List;

public class ReservationService implements main.Service.ReservationService {
  private ReservationRepoInterface reser;

    public ReservationService(ReservationRepoInterface reser) {
        this.reser = reser;
    }

    @Override
    public boolean checkRoomAvailability(Chambre room, LocalDate checkIn, LocalDate checkOut) {
        // Logic to check room availability using checkReservationOverlap
        List<Reservations> reservations = getReservationsByRoom(room);  // Fetch room reservations
        return checkReservationOverlap(reservations, room, checkIn, checkOut);
    }

    @Override
    public void addReservation(Reservations reservation) {
        // Logic to add the reservation to the system
        reser.createReservation(reservation);
    }

    // Add this method for checking if dates overlap
    public static boolean doDateRangesOverlap(LocalDate startDate1, LocalDate endDate1, LocalDate startDate2, LocalDate endDate2) {
        return !(startDate1.isAfter(endDate2) || endDate1.isBefore(startDate2));
    }

    @Override
    public boolean checkReservationOverlap(List<Reservations> reservations, Chambre room, LocalDate checkin, LocalDate checkout) {
        if (reservations.isEmpty()) {
            return true;  // No existing reservations, room is available
        } else {
            for (Reservations reservation : reservations) {
                boolean overlap = doDateRangesOverlap(reservation.getCheckin(), reservation.getCheckout(), checkin, checkout);
                if (overlap) {
                    return false;  // Date overlap found, room not available
                }
            }
            return true;  // No overlaps, room is available
        }
    }

    // Fetch reservations for the room (dummy implementation for now)
    private List<Reservations> getReservationsByRoom(Chambre room) {
        // Call the repository layer to get all reservations for the specific room
        return reser.findReservationsByRoom(room);
    }

    @Override
    public void createReservation(Reservations reservation) {
        if (reservation != null) {
            reser.createReservation(reservation);
        } else {
            throw new IllegalArgumentException("Reservation cannot be null");
        }
    }

    @Override
    public Reservations findReservationByid(int id) {
        return null;
    }

    @Override
    public List<Reservations> findReservationsByCnie(String cnie) {
        return reser.findReservationsByCnie(cnie);
    }

    @Override
    public void updateReservation(Reservations reservation) {
        reser.updateReservation(reservation);
    }

    @Override
    public void deleteReservation(int id) {
        Reservations r=findReservationByid(id);
        reser.deleteReservation(r);
    }

    @Override
    public List<Reservations> AllReservation() {
        return List.of();
    }
}

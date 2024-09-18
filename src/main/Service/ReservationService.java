package main.Service;
import main.Entity.Chambre;
import main.Entity.Reservations;

import java.time.LocalDate;
import java.util.List;

public interface ReservationService {

    void createReservation(Reservations reservation);


    Reservations findReservationByid(int id);

    boolean checkRoomAvailability(Chambre room, LocalDate checkIn, LocalDate checkOut);

    void addReservation(Reservations reservation);

    boolean checkReservationOverlap(List<Reservations> reservations, Chambre room, LocalDate checkin, LocalDate checkout);

    List<Reservations> AllReservation();

    List<Reservations> findReservationsByCnie(String cnie);


    void updateReservation(Reservations reservation);


    void deleteReservation(int id);



}


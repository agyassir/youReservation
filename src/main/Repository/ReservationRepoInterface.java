package main.Repository;

import main.Entity.Reservations;

import java.util.List;

public interface ReservationRepoInterface {
    void createReservation(Reservations reservation);





    List<Reservations> findReservationsByCnie(String cnie);


    void updateReservation(Reservations reservation);


    void deleteReservation(Reservations reservation);



}

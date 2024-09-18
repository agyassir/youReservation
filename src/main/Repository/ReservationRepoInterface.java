package main.Repository;

import main.Entity.Chambre;
import main.Entity.Reservations;

import java.util.List;

public interface ReservationRepoInterface {
    void createReservation(Reservations reservation);


    Reservations findReservationByid(int id);


    List<Reservations> findReservationsByCnie(String cnie);


    void updateReservation(Reservations reservation);


    void deleteReservation(Reservations reservation);

    List<Reservations>findReservationsByRoom(Chambre room);

    List <Reservations> AllResrvation();


}

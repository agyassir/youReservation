package main.Classes;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Reservations {
    private int id = 0;
    private User user;
    private Chambre room;
    private LocalDate checkin;
    private LocalDate checkout;
    private static int numberCounter=0;

    public Reservations(User user, Chambre room,LocalDate checkin, LocalDate checkout) {
        this.id++;
        this.user = user;
        this.room = room;
        this.checkin = checkin;
        this.checkout = checkout;
    }


    public LocalDate getCheckin() {
        return checkin;
    }

    public void setCheckin(LocalDate checkin) {
        this.checkin = checkin;
    }

    public LocalDate getCheckout() {
        return checkout;
    }

    public void setCheckout(LocalDate checkout) {
        this.checkout = checkout;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Chambre getRoom() {
        return room;
    }

    public void setRoom(Chambre room) {
        this.room = room;
    }

    public static boolean doDateRangesOverlap(LocalDate startDate1, LocalDate endDate1, LocalDate startDate2, LocalDate endDate2) {
        // Check if the ranges overlap
        return !(startDate1.isAfter(endDate2) || endDate1.isBefore(startDate2));
    }

    public boolean check(ArrayList<Reservations> reservations, Chambre room, LocalDate checkin, LocalDate checkout){

        if (reservations.isEmpty()){
            return true;
        }else {
            for (Reservations reservation : reservations) {
                boolean overlap = doDateRangesOverlap(reservation.getCheckin(), reservation.getCheckout(), checkin, checkout);
                if (overlap) {
                    return false;
                }
            }

            return true;
        }

    }


}

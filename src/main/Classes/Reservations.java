package main.Classes;
import java.time.LocalDate;
import java.util.List;

public class Reservations {
    private int id = 0;
    private User user;
    private Chambre room;
    private LocalDate checkin;
    private LocalDate checkout;

    public Reservations(User user, Chambre room,LocalDate checkin, LocalDate checkout) {
        this.id++;
        this.user = user;
        this.room = room;
        this.checkin = checkin;
        this.checkout = checkout;
    }

    public Reservations reserver(User user,Chambre room ,LocalDate checkin, LocalDate checkout){
        Reservations reservation=new Reservations(user,room,checkin,checkout);
        return reservation;
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

    public static boolean doDateRangesOverlap(LocalDate startDate1, LocalDate endDate1, LocalDate startDate2, LocalDate endDate2) {
        // Check if the ranges overlap
        return !(startDate1.isAfter(endDate2) || endDate1.isBefore(startDate2));
    }

    public int check(List<Reservations> reservations, Chambre room, LocalDate checkin, LocalDate checkout){
        boolean overlap;

        for (Reservations reservation : reservations) {
           overlap=doDateRangesOverlap(reservation.getCheckin(),reservation.getCheckout(),checkin,checkout)
        }


        return 1;
    }

}

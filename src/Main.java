import main.Classes.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static ArrayList<Reservations> reservationsList = new ArrayList<>();
    public static ArrayList<Hotel> Hotels = new ArrayList<>();
    public static ArrayList<Chambre> rooms = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        Hotel firstHotel = new Hotel("Ronaldo's Hotel", "Marrakech");
        Chambre room1 = new Chambre("Double Bed", firstHotel, 20);
        Chambre room2 = new Chambre("VIP", firstHotel, 10);
        Chambre room3 = new Chambre("Single Queen Bed", firstHotel, 50);
        Hotels.add(firstHotel);
        rooms.add(room1);
        rooms.add(room2);
        rooms.add(room3);
//System.out.println(room3.getId());
        menu();
    }

    public static void reservation(Chambre room) {
        System.out.print("Enter your first name: ");
        scanner.nextLine();
        String firstName = scanner.nextLine();  // Capture first name

        // Ask for the user's last name
        System.out.print("Enter your last name: ");
        String lastName = scanner.nextLine();  // Capture last name

        // Ask for the user's CNIE
        System.out.print("Enter your CNIE: ");
        String cnie = scanner.nextLine();

        User user = new User(cnie, firstName, lastName);

        System.out.print("Enter your check-in date (dd-MM-yyyy): ");
        String checkInInput = scanner.nextLine();
        LocalDate checkInDate = LocalDate.parse(checkInInput, DateTimeFormatter.ofPattern("dd-MM-yyyy"));

        // Prompt for check-out date
        System.out.print("Enter your check-out date (dd-MM-yyyy): ");
        String checkOutInput = scanner.nextLine();
        LocalDate checkOutDate = LocalDate.parse(checkOutInput, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        Reservations reservation = new Reservations(user, room, checkInDate, checkOutDate);
        boolean check = reservation.check(reservationsList, room, checkInDate, checkOutDate);
        if (check) {
            reservationsList.add(reservation);
        } else {
            System.out.println("the room isnt available at that time");
            menu();
        }

    }

    public static void roomsavailable(Hotel hotel) {

        ArrayList<Chambre> filteredRooms = new ArrayList<>();

        // Iterate through the rooms and check the hotel ID
        for (Chambre room : rooms) {
            if (room.getHotel().getId() == hotel.getId()) {
                filteredRooms.add(room);
            }
        }
        if (filteredRooms.isEmpty()) {
            System.out.println("No hotels available.");
        } else {
            System.out.println("0.back to the main menu");
            filteredRooms.forEach(e -> System.out.println(e.getType()));
            System.out.println("==========choose an a room you want to reserve==========");
            int choix = scanner.nextInt();
            if (choix == 0) {
                menu();
            } else {
                reservation(filteredRooms.get(choix - 1));
            }
        }


    }

    public static void viewHotel() {
        Affichage affichage = new Affichage();
        List<Hotel> hotelList = affichage.viewHotel(Hotels);

        // Display hotel names
        if (hotelList.isEmpty()) {
            System.out.println("No hotels available.");
        } else {
            hotelList.forEach(e -> System.out.println(e.getName()));
            System.out.println("choose an hotel");

            int choix = scanner.nextInt();

            roomsavailable(hotelList.get(choix - 1));

        }
    }

    public static void menu() {
        int choice;
        do {
            System.out.println("Hotel ");
            System.out.println("1. Check room availability");
            System.out.println("2. view your reservation");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline
            switch (choice) {
                case 1:
                    System.out.println("View the hotels available:");
                    viewHotel();
                    break;
                case 2:
                    System.out.println("View your reservations:");
                    viewReservation();
                    break;
                case 3:
                    System.out.println("Exiting the program...");
                    scanner.close();
                    return;  // Exit the program
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        while (choice != 3);


    }

    public static void deleteReservation(Reservations reservation) {
        reservationsList.removeIf(reservations -> reservations.getId() == reservation.getId());
        System.out.println("Reservation deleted successfully.");
    }

    public static void viewReservation() {
        System.out.println("Please enter your CNIE:");
          // Consume leftover newline character from previous input
        String CNIE = scanner.nextLine();  // Assign input to class-level CNIE variable
        ArrayList<Reservations> Myreservation = new ArrayList<>();

        // Iterate through the rooms and check the hotel ID
        for (Reservations reservations : reservationsList) {
            if (reservations.getUser().getCnie().equals(CNIE) ) {
                Myreservation.add(reservations);
            }
        }
        if (Myreservation.isEmpty()) {
            System.out.println("No resrvation available.");
        } else {
            System.out.println("==============your reservation================");
            Myreservation.forEach(e -> {
                System.out.println(e.getRoom().getType() + " " + e.getRoom().getHotel().getName() + " " + e.getCheckin() + " " + e.getCheckout()+" by "+ e.getUser().getLastName()+" "+e.getUser().getFirstName() );
            });
            System.out.println("==============0.back to the main menu================");
            System.out.println("==============1.update a reservation================");
            System.out.println("==============2.delete a reservation================");
            int choix = scanner.nextInt();
            switch (choix) {
                case 0:
                menu();
                break;
                case 1:
                    System.out.println("==============choose a reservation to change================");
                    int choice = scanner.nextInt();
                    updateReservation(Myreservation.get(choice-1));
                break;
                case 2:
                    System.out.println("==============choose a reservation to delete================");
                     choice = scanner.nextInt();
                    deleteReservation(Myreservation.get(choice-1));
                    break;
                default:
                System.out.println("your choice may be incorrect");
                break;
            }
            return;
            // Add your logic here to retrieve reservations for the provided CNIE
        }


    }

    public static void updateReservation(Reservations reservation) {
        for (Reservations reservations : reservationsList) {
            if (reservations.getId() == reservation.getId()) {

                System.out.println("Update Reservation");
                System.out.println("1. Update First Name");
                System.out.println("2. Update Last Name");
                System.out.println("3. Update CNIE");
                System.out.println("4. Update Check-in Date");
                System.out.println("5. Update Check-out Date");
                System.out.println("6. Cancel Update");
                System.out.print("Enter your choice: ");

                int choice = scanner.nextInt();
                scanner.nextLine(); // Clear the buffer

                switch (choice) {
                    case 1:
                        System.out.print("Enter new first name (leave blank to keep current): ");
                        String newFirstName = scanner.nextLine();
                        if (!newFirstName.isEmpty()) {
                            reservations.getUser().setFirstName(newFirstName);
                        }
                        break;
                    case 2:
                        System.out.print("Enter new last name (leave blank to keep current): ");
                        String newLastName = scanner.nextLine();
                        if (!newLastName.isEmpty()) {
                            reservations.getUser().setLastName(newLastName);
                        }
                        break;
                    case 3:
                        System.out.print("Enter new CNIE (leave blank to keep current): ");
                        String newCnie = scanner.nextLine();
                        if (!newCnie.isEmpty()) {
                            reservations.getUser().setCnie(newCnie);
                        }
                        break;
                    case 4:
                        System.out.print("Enter new check-in date (dd-MM-yyyy) (leave blank to keep current): ");
                        String checkInInput = scanner.nextLine();
                        if (!checkInInput.isEmpty()) {
                            LocalDate newCheckInDate = LocalDate.parse(checkInInput, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                            reservations.setCheckin(newCheckInDate);
                        }
                        break;
                    case 5:
                        System.out.print("Enter new check-out date (dd-MM-yyyy) (leave blank to keep current): ");
                        String checkOutInput = scanner.nextLine();
                        if (!checkOutInput.isEmpty()) {
                            LocalDate newCheckOutDate = LocalDate.parse(checkOutInput, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                            reservations.setCheckout(newCheckOutDate);
                        }
                        break;
                    case 6:
                        System.out.println("Update canceled.");
                        return;
                    default:
                        System.out.println("Invalid choice. No updates made.");
                        return;
                }
                reservationsList.set(reservationsList.indexOf(reservation),reservation);
                System.out.println("Reservation updated successfully.");
            }
        }
    }



}



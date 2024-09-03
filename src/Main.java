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
    public static Affichage menu = new Affichage();
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

        System.out.print("Enter your check-in date (yyyy-MM-dd): ");
        String checkInInput = scanner.nextLine();
        LocalDate checkInDate = LocalDate.parse(checkInInput, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        // Prompt for check-out date
        System.out.print("Enter your check-out date (yyyy-MM-dd): ");
        String checkOutInput = scanner.nextLine();
        LocalDate checkOutDate = LocalDate.parse(checkOutInput, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
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
            System.out.println("2. Add new reservation");
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
                System.out.println(e.getRoom().getType() + " " + e.getRoom().getHotel().getName() + " " + e.getCheckin() + " " + e.getCheckout());
            });
            System.out.println("==============0.back to the main menu================");
            int choix = scanner.nextInt();
            if (choix == 0) {
                menu();
            } else {
                System.out.println("your choice may be incorrect");
            }
            return;
            // Add your logic here to retrieve reservations for the provided CNIE
        }


    }
}
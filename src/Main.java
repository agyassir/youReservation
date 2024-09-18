import main.Entity.*;
import main.Repository.ChamberRepoInterface;
import main.Repository.HotelRepoInterface;
import main.Repository.Implementation.ChamberRepoImpl;
import main.Repository.Implementation.HotelRepoImple;
import main.Repository.Implementation.ReservationRepoImpl;
import main.Repository.Implementation.UserRepositoryImpl;
import main.Repository.ReservationRepoInterface;
import main.Repository.UserRepositoryInterface;
import main.Service.ChamberServiceInterface;
import main.Service.HotelServiceInterface;
import main.Service.Implementation.ChamberServiceImpl;
import main.Service.Implementation.HotelServiceImpl;
import main.Service.Implementation.UserServiceImpl;
import main.Service.ReservationService;
import main.Service.UserServiceInterface;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {


    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {


        UserRepositoryInterface userRepository = new UserRepositoryImpl();
        UserServiceInterface userService = new UserServiceImpl(userRepository);
        ChamberRepoInterface chamberrepo=new ChamberRepoImpl();
        ChamberServiceInterface chamberservice=new ChamberServiceImpl(chamberrepo);
        HotelRepoInterface hotelRepo = new HotelRepoImple();
        HotelServiceInterface hotelService = new HotelServiceImpl(hotelRepo);
        ReservationRepoInterface resRepo= new ReservationRepoImpl();
        ReservationService  reservationService=new main.Service.Implementation.ReservationService(resRepo);
        menu(hotelService,chamberservice,reservationService,userService);

    }

    public static void reservation(Chambre room,HotelServiceInterface hotelService,ChamberServiceInterface chamberservice,ReservationService reservationService,UserServiceInterface userService) {
        System.out.print("Enter your CNIE: ");
        scanner.nextLine();
        String cnie = scanner.nextLine();
        Customer user= userService.checkUser(cnie);
        if(user.in){

        }
        else {

            System.out.print("Enter your first name: ");

            String firstName = scanner.nextLine();  // Capture first name

            // Ask for the user's last name
            System.out.print("Enter your last name: ");
            String lastName = scanner.nextLine();  // Capture last name

            // Ask for the user's CNIE


            Customer user = new Customer(cnie, firstName, lastName);
        }
        System.out.print("Enter your check-in date (dd-MM-yyyy): ");
        String checkInInput = scanner.nextLine();
        LocalDate checkInDate = LocalDate.parse(checkInInput, DateTimeFormatter.ofPattern("dd-MM-yyyy"));

        // Prompt for check-out date
        System.out.print("Enter your check-out date (dd-MM-yyyy): ");
        String checkOutInput = scanner.nextLine();
        LocalDate checkOutDate = LocalDate.parse(checkOutInput, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        Reservations reservation = new Reservations(user, room, checkInDate, checkOutDate);
        List<Reservations> reservationsList=reservationService.AllReservation();
        boolean check = reservationService.checkReservationOverlap(reservationsList, room, checkInDate, checkOutDate);
        if (check) {
            reservationService.addReservation(reservation);
        } else {
            System.out.println("the room isnt available at that time");
            menu(hotelService,chamberservice,reservationService,userService);
        }

    }

    public static void roomsavailable(Hotel hotel,HotelServiceInterface hotelService,ChamberServiceInterface chamberservice,ReservationService reservationService,UserServiceInterface userService) {

        List<Chambre> filteredRooms = chamberservice.findChambersByHotel(hotel);

        if (filteredRooms.isEmpty()) {
            System.out.println("No hotels available.");
        } else {
            System.out.println("0.back to the main menu");
            System.out.println("===============================================");
            filteredRooms.forEach(e -> System.out.println(e.getType()+" "+e.getPrix()+"DH"));
            System.out.println("==========choose an a room you want to reserve==========");
            int choix = scanner.nextInt();
            if (choix == 0) {
                menu(hotelService,chamberservice,reservationService,userService);
            } else {
                reservation(filteredRooms.get(choix - 1),hotelService,chamberservice,reservationService,userService);
            }
        }


    }

    public static void viewHotel(HotelServiceInterface hotelService,ChamberServiceInterface chamberservice,ReservationService reservationService,UserServiceInterface userService) {
        List<Hotel> hotelList = hotelService.findAllHotels();

        if (hotelList.isEmpty()) {
            System.out.println("No hotels available.");
        } else {
            hotelList.forEach(e -> System.out.println(e.getName()));
            System.out.println("choose an hotel");

            int choix = scanner.nextInt();

            roomsavailable(hotelList.get(choix - 1),hotelService,chamberservice,reservationService,userService);

        }
    }

    public static void menu(HotelServiceInterface hotelService,ChamberServiceInterface chamberservice,ReservationService reservationService,UserServiceInterface userService) {
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
                    viewHotel(hotelService,chamberservice,reservationService,userService);
                    break;
                case 2:
                    System.out.println("View your reservations:");
                    viewReservation(hotelService,chamberservice,reservationService,userService);
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

    public static void deleteReservation(Reservations reservation,ReservationService reservationService) {
        reservationService.deleteReservation(reservation.getId());
        System.out.println("Reservation deleted successfully.");
    }

    public static void viewReservation(HotelServiceInterface hotelService,ChamberServiceInterface chamberservice,ReservationService reservationService,UserServiceInterface userService) {
        System.out.println("Please enter your CNIE:");
          // Consume leftover newline character from previous input
        String CNIE = scanner.nextLine();  // Assign input to class-level CNIE variable
        List<Reservations> Myreservation = reservationService.findReservationsByCnie(CNIE);

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
                menu(hotelService,chamberservice,reservationService,userService);
                break;
                case 1:
                    System.out.println("==============choose a reservation to change================");
                    int choice = scanner.nextInt();
                    updateReservation(Myreservation.get(choice-1),reservationService);
                break;
                case 2:
                    System.out.println("==============choose a reservation to delete================");
                     choice = scanner.nextInt();
                    deleteReservation(Myreservation.get(choice-1),reservationService);
                    break;
                default:
                System.out.println("your choice may be incorrect");
                break;
            }
           }


    }

    public static void updateReservation(Reservations reservation,ReservationService reservationService) {
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
                        System.out.print("Enter new check-in date (dd-MM-yyyy) (leave blank to keep current): ");
                        String checkInInput = scanner.nextLine();
                        if (!checkInInput.isEmpty()) {
                            LocalDate newCheckInDate = LocalDate.parse(checkInInput, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                            if (!newCheckInDate.isAfter(reservation.getCheckout())){
                            reservation.setCheckin(newCheckInDate);}
                            else{
                                System.out.println("you cannot change the checkin to after the checkout,please change the chekcout first");
                            updateReservation(reservation,reservationService);
                            }
                        }
                        break;
                    case 5:
                        System.out.print("Enter new check-out date (dd-MM-yyyy) (leave blank to keep current): ");
                        String checkOutInput = scanner.nextLine();
                        if (!checkOutInput.isEmpty()) {
                            LocalDate newCheckOutDate = LocalDate.parse(checkOutInput, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                            reservation.setCheckout(newCheckOutDate);
                        }
                        break;
                    case 6:
                        System.out.println("Update canceled.");
                        return;
                    default:
                        System.out.println("Invalid choice. No updates made.");
                        return;
                }
                reservationService.updateReservation(reservation);
                System.out.println("Reservation updated successfully.");
            }
        }








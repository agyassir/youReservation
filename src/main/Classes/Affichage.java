package main.Classes;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Affichage {
    Scanner scanner = new Scanner(System.in);

    private String CNIE;


    // Method to view hotel information
    public List<Hotel> viewHotel(ArrayList<Hotel> hotels) {
        if(hotels == null){
            return new ArrayList<>();
        }
            return  hotels.stream().collect(Collectors.toList());
    }

    // Method to view reservations based on CNIE

}

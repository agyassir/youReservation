package main.Repository;

import main.Entity.Chambre;
import main.Entity.Hotel;
import java.util.List;

public interface HotelRepoInterface {

    // Create a new hotel
    void createHotel(Hotel hotel);

    // Find a hotel by its ID
    Hotel findHotelById(int id);

    // Find all hotels
    List<Hotel> findAllHotels();

    // Update a hotel's information
    void updateHotel(Hotel hotel);

    // Delete a hotel by its ID
    void deleteHotel(int id);

}

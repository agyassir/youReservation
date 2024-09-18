package main.Service;

import main.Entity.Hotel;
import java.util.List;

public interface HotelServiceInterface {


    void createHotel(Hotel hotel);


    Hotel findHotelById(int id);


    List<Hotel> findAllHotels();


    void updateHotel(Hotel hotel);


    void deleteHotel(int id);

}

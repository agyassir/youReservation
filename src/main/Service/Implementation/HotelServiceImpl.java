package main.Service.Implementation;

import main.Entity.Hotel;
import main.Repository.HotelRepoInterface;
import main.Service.HotelServiceInterface;

import java.util.List;

public class HotelServiceImpl implements HotelServiceInterface {

    private final HotelRepoInterface hotelRepo;

    // Constructor injection for the repository
    public HotelServiceImpl(HotelRepoInterface hotelRepo) {
        this.hotelRepo = hotelRepo;
    }

    @Override
    public void createHotel(Hotel hotel) {
        if (hotel != null && hotel.getName() != null && !hotel.getName().isEmpty()) {
            // Additional business logic can be added here, such as validation
            hotelRepo.createHotel(hotel);
        } else {
            throw new IllegalArgumentException("Invalid hotel information");
        }
    }

    @Override
    public Hotel findHotelById(int id) {
        if (id > 0) {
            return hotelRepo.findHotelById(id);
        } else {
            throw new IllegalArgumentException("Invalid hotel ID");
        }
    }

    @Override
    public List<Hotel> findAllHotels() {
        return hotelRepo.findAllHotels();
    }

    @Override
    public void updateHotel(Hotel hotel) {
        if (hotel != null && hotel.getId() > 0) {
            hotelRepo.updateHotel(hotel);
        } else {
            throw new IllegalArgumentException("Invalid hotel data for update");
        }
    }

    @Override
    public void deleteHotel(int id) {
        if (id > 0) {
            hotelRepo.deleteHotel(id);
        } else {
            throw new IllegalArgumentException("Invalid hotel ID for deletion");
        }
    }
}

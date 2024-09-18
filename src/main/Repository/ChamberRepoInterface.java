package main.Repository;

import main.Entity.Chambre;
import main.Entity.Hotel;

import java.util.List;

public interface ChamberRepoInterface {
    void createChamber(Chambre chambre);


    Chambre findChamberById(int id);


    List<Chambre> findChambersByHotelId(Hotel hotel);


    void updateChamber(Chambre chambre);


    void deleteChamber(int id);


}

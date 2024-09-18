package main.Service;

import main.Entity.Chambre;
import main.Entity.Hotel;

import java.util.List;

public interface ChamberServiceInterface {

    void createChamber(Chambre chambre);

    Chambre findChamberById(int id);

    List<Chambre> findChambersByHotel(Hotel hotel);

    void updateChamber(Chambre chambre);

    void deleteChamber(int id);
}

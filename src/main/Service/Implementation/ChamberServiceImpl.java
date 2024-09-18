package main.Service.Implementation;

import main.Entity.Chambre;
import main.Entity.Hotel;
import main.Repository.ChamberRepoInterface;
import main.Service.ChamberServiceInterface;

import java.util.List;

public class ChamberServiceImpl implements ChamberServiceInterface {
    private final ChamberRepoInterface chamberRepo;

    // Constructor injection for the repository
    public ChamberServiceImpl(ChamberRepoInterface chamberRepo) {
        this.chamberRepo = chamberRepo;
    }

    @Override
    public void createChamber(Chambre chambre) {
        if (chambre != null) {
            // Business logic can go here
            chamberRepo.createChamber(chambre);
        } else {
            throw new IllegalArgumentException("Chambre cannot be null");
        }
    }

    @Override
    public Chambre findChamberById(int id) {
        // Apply business rules, if any, before accessing the repository
        return chamberRepo.findChamberById(id);
    }

    @Override
    public List<Chambre> findChambersByHotel(Hotel hotel) {

        return chamberRepo.findChambersByHotelId(hotel);
    }

    @Override
    public void updateChamber(Chambre chambre) {
        if (chambre != null && chambre.getId() > 0) {
            chamberRepo.updateChamber(chambre);
        } else {
            throw new IllegalArgumentException("Chambre must have a valid ID");
        }
    }

    @Override
    public void deleteChamber(int id) {
        if (id > 0) {
            chamberRepo.deleteChamber(id);
        } else {
            throw new IllegalArgumentException("Invalid chamber ID");
        }
    }
}

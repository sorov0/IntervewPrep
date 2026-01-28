package LLDProblems.Youtube.ParkingLotSystem.Code2;

import java.util.List;

public abstract class ParkingSpotManager {

    List<ParkingSpot> spots;

    ParkingSpotManager(List<ParkingSpot> spots) {
        this.spots = spots;
    }

    abstract void findParkingSpace(ParkingStrategy ps);
    abstract void addParkingSpace();
    abstract void removeParkingSpace();
    abstract void parkVehicle();
    abstract void removeVehicle();


}

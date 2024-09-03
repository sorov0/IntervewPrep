package LLDProblems.ParkingLotSystem.Code2;

import java.util.List;

public class FourWheelerParkingSpotManager extends ParkingSpotManager{

    FourWheelerParkingSpotManager(List<ParkingSpot> spots) {
        super(spots);
    }



    @Override
    void findParkingSpace(ParkingStrategy ps) {
        ps.find();
    }

    @Override
    void addParkingSpace() {

    }

    @Override
    void removeParkingSpace() {

    }

    @Override
    void parkVehicle() {

    }

    @Override
    void removeVehicle() {

    }
}

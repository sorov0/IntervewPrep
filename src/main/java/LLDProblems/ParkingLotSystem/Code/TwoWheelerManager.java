package LLDProblems.ParkingLotSystem.Code;

import java.util.List;

public class TwoWheelerManager extends ParkingSpotManager{


    TwoWheelerManager(List<ParkingSpot> spots) {
        super(spots);
    }

    @Override
    ParkingSpot findParkingSpace() {
        // Implementation to find nearest parking spot for Two Wheelers
        return null;
    }
}

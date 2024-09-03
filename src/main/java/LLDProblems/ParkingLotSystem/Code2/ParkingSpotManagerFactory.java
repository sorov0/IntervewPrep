package LLDProblems.ParkingLotSystem.Code2;

import java.util.List;

public class ParkingSpotManagerFactory {

    ParkingSpotManager getParkingSpotManager(VehicleType vehicleType, List<ParkingSpot> spots) {

        if (vehicleType == VehicleType.TwoWheeler)
            return new TwoWheelerParkingSpotManager(spots);
        else if (vehicleType == VehicleType.FourWheeler)
            return new TwoWheelerParkingSpotManager(spots);
        else
            return null; // Handle error
    }
}

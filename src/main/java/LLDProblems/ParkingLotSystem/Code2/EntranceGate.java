package LLDProblems.ParkingLotSystem.Code2;

import java.util.List;

public class EntranceGate {

    ParkingSpotManagerFactory parkingSpotManagerFactory;
    ParkingSpotManager parkingSpotManager;

    Ticket ticket;

    void findParkingSpace(Vehicle vehicle , List<ParkingSpot> ps){

        parkingSpotManagerFactory.getParkingSpotManager(vehicle.vehicleType , ps)
                .findParkingSpace(new NearToEntranceStrategy());

    }

    void bookSpot(Vehicle vehicle){

    }

    void updateParkingSpace(){

    }
    void generateTicket(Vehicle vehicle , ParkingSpot ps){

    }
}

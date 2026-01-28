package LLDProblems.Youtube.ParkingLotSystem.Code2;

public class ParkingSpot {

    int id;
    boolean isEmpty;
    Vehicle vehicle;
    int price;


    void parkVehicle(Vehicle vehicle){
        this.vehicle = vehicle;
        isEmpty = false;
    }

    void removeVehicle(Vehicle vehicle){
        this.vehicle = null;
        isEmpty = true;
    }
}

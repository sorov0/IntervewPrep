package LLDProblems.Youtube.ParkingLotSystem.Code3.vehicle;

public class BikeVehicle extends Vehicle{

    public static final double RATE = 10.0; // per hour


    public BikeVehicle(String licensePlate) {
        super(licensePlate, "Bike");
    }

    @Override
    public double calculateFee(int hourStayed) {
        return RATE * hourStayed;
    }
}

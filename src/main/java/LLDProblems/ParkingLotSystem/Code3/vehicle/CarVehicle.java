package LLDProblems.ParkingLotSystem.Code3.vehicle;

public class CarVehicle extends Vehicle{

    public static final double RATE = 10.0; // per hour

    public CarVehicle(String licensePlate) {
        super(licensePlate, "Car");
    }

    @Override
    public double calculateFee(int hourStayed) {
        return RATE * hourStayed;
    }
}

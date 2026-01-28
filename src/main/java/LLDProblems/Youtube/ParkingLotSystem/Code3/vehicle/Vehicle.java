package LLDProblems.Youtube.ParkingLotSystem.Code3.vehicle;

public abstract class Vehicle {

    private String licensePlate;
    private String VehicleType;


    public Vehicle(String licensePlate, String vehicleType) {
        this.licensePlate = licensePlate;
        VehicleType = vehicleType;
    }

    public abstract double calculateFee(int hourStayed);

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getVehicleType() {
        return VehicleType;
    }

    public void setVehicleType(String vehicleType) {
        VehicleType = vehicleType;
    }
}

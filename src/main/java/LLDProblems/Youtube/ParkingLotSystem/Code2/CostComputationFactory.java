package LLDProblems.Youtube.ParkingLotSystem.Code2;

public class CostComputationFactory {

    CostComputation getCostComputation(Ticket ticket){
        if(ticket.vehicle.vehicleType.equals(VehicleType.TwoWheeler)){
            return new TwoWheelerCostComputation(new HourlyPricingStrategy());
        } else if (ticket.vehicle.vehicleType.equals(VehicleType.FourWheeler)) {
            return new TwoWheelerCostComputation(new MinuteBasedPricingStrategy());
        }else {
            return null;
        }
    }
}

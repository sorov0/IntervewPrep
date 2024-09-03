package LLDProblems.ParkingLotSystem.Code2;

public class TwoWheelerCostComputation extends CostComputation{

    public TwoWheelerCostComputation(PricingStrategy pricingStrategy) {
        super(new HourlyPricingStrategy());
    }
}

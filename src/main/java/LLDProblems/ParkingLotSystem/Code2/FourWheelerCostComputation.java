package LLDProblems.ParkingLotSystem.Code2;

public class FourWheelerCostComputation extends CostComputation{

    public FourWheelerCostComputation(PricingStrategy pricingStrategy) {
        super(new MinuteBasedPricingStrategy());
    }
}

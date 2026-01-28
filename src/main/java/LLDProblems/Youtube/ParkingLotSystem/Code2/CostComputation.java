package LLDProblems.Youtube.ParkingLotSystem.Code2;

public class CostComputation {

    PricingStrategy pricingStrategy;

    public CostComputation(PricingStrategy pricingStrategy) {
        this.pricingStrategy = pricingStrategy;
    }

    int getPrice(Ticket ticket) {
        return pricingStrategy.getPrice(ticket);
    }

}

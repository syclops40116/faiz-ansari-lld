package design_problems.personal.due.splitwise.strategy;

import design_problems.personal.due.splitwise.model.Split;

import java.util.List;

public class PercentageSplitStrategy implements SplitStrategy {


    private final List<Double> percentages;


    public PercentageSplitStrategy(List<Double> percentages) {
        this.percentages = percentages;
    }


    @Override
    public void calculate(double total, List<Split> splits) {


        if (splits.size() != percentages.size()) {
            throw new RuntimeException("Invalid percentages");
        }


        double sum = 0;
        for (double p : percentages) sum += p;


        if (Math.abs(sum - 100.0) > 0.01) {
            throw new RuntimeException("Percentages must sum to 100");
        }


        for (int i = 0; i < splits.size(); i++) {
            double amt = (total * percentages.get(i)) / 100;
            splits.get(i).setAmount(amt);
        }
    }
}
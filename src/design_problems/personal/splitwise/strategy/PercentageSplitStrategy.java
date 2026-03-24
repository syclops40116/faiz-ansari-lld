package design_problems.personal.splitwise.strategy;

import design_problems.personal.splitwise.model.Split;

import java.util.List;

public class PercentageSplitStrategy implements SplitStrategy{

    @Override
    public void calculate(double total, List<Split> splits) {
        if (splits == null || splits.isEmpty()) {
            throw new IllegalArgumentException("Splits cannot be empty");
        }

        int totalPercentage = 0;
        for (Split split: splits) {
            totalPercentage += split.percentage;
        }

        if(totalPercentage != 100) {
            throw new RuntimeException("Invalid split percentages. Total must be 100%.");
        }

        // Calculate amount for each split
        for (Split split : splits) {
            split.amount = (total * split.percentage) / 100.0;;
        }
    }
}

package design_problems.personal.splitwise.strategy;

import design_problems.personal.splitwise.model.Split;
import java.util.List;

public class EqualSplitStrategy implements SplitStrategy{

    @Override
    public void calculate(double total, List<Split> splits) {

        if (splits == null || splits.isEmpty()) {
            throw new IllegalArgumentException("Splits cannot be empty");
        }

        double share = total / splits.size();

        for (Split split : splits) {
            split.amount = share;
        }
    }
}

package design_problems.personal.due.splitwise.strategy;

import design_problems.personal.due.splitwise.model.Split;

import java.util.List;

public class EqualSplitStrategy implements SplitStrategy {


    @Override
    public void calculate(double total, List<Split> splits) {
        double share = total / splits.size();


        for (Split s : splits) {
            s.setAmount(share);
        }
    }
}
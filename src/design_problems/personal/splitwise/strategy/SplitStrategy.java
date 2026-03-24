package design_problems.personal.splitwise.strategy;

import design_problems.personal.splitwise.model.Split;

import java.util.List;

public interface SplitStrategy {
    void calculate(double total, List<Split> splits);
}

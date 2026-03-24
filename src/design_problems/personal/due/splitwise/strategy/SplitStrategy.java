package design_problems.personal.due.splitwise.strategy;

import design_problems.personal.due.splitwise.model.Split;

import java.util.List;

public interface SplitStrategy {
    void calculate(double total, List<Split> splits);
}
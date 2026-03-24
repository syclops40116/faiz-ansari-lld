package design_problems.personal.splitwise.factory;

import design_problems.personal.splitwise.enums.SplitType;
import design_problems.personal.splitwise.strategy.EqualSplitStrategy;
import design_problems.personal.splitwise.strategy.PercentageSplitStrategy;
import design_problems.personal.splitwise.strategy.SplitStrategy;

public class SplitStrategyFactory {

    public static SplitStrategy createSplitStrategy(SplitType splitType) {
        switch (splitType) {
            case EQUAL -> {
                return new EqualSplitStrategy();
            }
            case PERCENTAGE -> {
                return new PercentageSplitStrategy();
            }
            default -> throw new RuntimeException("No valid splitType");
        }
    }
}

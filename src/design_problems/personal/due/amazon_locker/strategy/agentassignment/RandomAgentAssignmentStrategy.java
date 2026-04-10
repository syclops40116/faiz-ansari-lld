package design_problems.personal.due.amazon_locker.strategy.agentassignment;

import design_problems.personal.due.amazon_locker.model.DeliveryAgent;

import java.util.List;
import java.util.Random;

public class RandomAgentAssignmentStrategy implements AgentAssignmentStrategy {
    private final Random random = new Random();

    @Override
    public DeliveryAgent assignAgent(List<DeliveryAgent> agents) {
        if(agents.isEmpty()) return null;
        return agents.get(random.nextInt(agents.size()));
    }
}

package design_problems.personal.due.amazon_locker.strategy.agentassignment;

import design_problems.personal.due.amazon_locker.model.DeliveryAgent;

import java.util.List;

public interface AgentAssignmentStrategy {
    DeliveryAgent assignAgent(List<DeliveryAgent> agents);
}

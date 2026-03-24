package design_problems.personal.due.amazon_locker;

import java.util.List;

public interface AgentAssignmentStrategy {
    DeliveryAgent assignAgent(List<DeliveryAgent> agents);
}

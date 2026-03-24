package design_problems.personal.due.amazon_locker;

import java.util.List;

public class AgentService {
    AgentAssignmentStrategy strategy;
    AgentRepository repository;

    public AgentService(AgentAssignmentStrategy agentAssignmentStrategy) {
        strategy = agentAssignmentStrategy;
        repository = new AgentRepository();
    }

    public DeliveryAgent assignAgentForDelivery(Locker locker, Package pkg) {
        String zipcode = locker.zipcode;
        List<DeliveryAgent> agents = repository.getByZip(zipcode);

        if(agents == null || agents.isEmpty()) {
            throw new RuntimeException("No delivery agent found for delivery");
        }

        DeliveryAgent assignedAgent = strategy.assignAgent(agents);

        if(assignedAgent == null) {
            throw new RuntimeException("Could not assign the delivery agent");
        }

        System.out.println("Package has been assigned to " + assignedAgent.agentId);

        return assignedAgent;
    }
}

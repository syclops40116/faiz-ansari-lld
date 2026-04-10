package design_problems.personal.due.amazon_locker.service;

import design_problems.personal.due.amazon_locker.model.DeliveryAgent;
import design_problems.personal.due.amazon_locker.model.Locker;
import design_problems.personal.due.amazon_locker.model.Package;
import design_problems.personal.due.amazon_locker.repository.AgentRepository;
import design_problems.personal.due.amazon_locker.strategy.agentassignment.AgentAssignmentStrategy;

import java.util.List;

public class AgentService {
    AgentAssignmentStrategy strategy;
    AgentRepository repository;

    public AgentService(AgentAssignmentStrategy agentAssignmentStrategy) {
        strategy = agentAssignmentStrategy;
        repository = new AgentRepository();
    }

    public DeliveryAgent assignAgentForDelivery(Locker locker, Package pkg) {
        String zipcode = locker.getZipcode();
        List<DeliveryAgent> agents = repository.getByZip(zipcode);

        if(agents == null || agents.isEmpty()) {
            throw new RuntimeException("No delivery agent found for delivery");
        }

        DeliveryAgent assignedAgent = strategy.assignAgent(agents);

        if(assignedAgent == null) {
            throw new RuntimeException("Could not assign the delivery agent");
        }

        System.out.println("Package has been assigned to " + assignedAgent.getAgentId());

        return assignedAgent;
    }
}

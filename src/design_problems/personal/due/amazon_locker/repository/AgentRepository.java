package design_problems.personal.due.amazon_locker.repository;

import design_problems.personal.due.amazon_locker.model.DeliveryAgent;

import java.util.*;

public class AgentRepository {
    Map<String, DeliveryAgent> agentsMap;
    Map<String, List<DeliveryAgent>> zipToAgents;

    public AgentRepository() {
        agentsMap = new HashMap<>();
        zipToAgents = new HashMap<>();
    }

    public void save(DeliveryAgent agent) {
        agentsMap.put(agent.getAgentId(), agent);
        for(String zip: agent.getServiceableZipcodes()) {
            List<DeliveryAgent> agentList = zipToAgents.getOrDefault(zip, new ArrayList<>());
            agentList.add(agent);
            zipToAgents.put(zip, agentList);
        }
    }

    public DeliveryAgent getById(String id) {return agentsMap.get(id);}

    public List<DeliveryAgent> getByZip(String zip) {
        return zipToAgents.getOrDefault(zip, Collections.emptyList());
    }
}

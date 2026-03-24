package design_problems.personal.due.amazon_locker;

import java.util.*;

public class AgentRepository {
    Map<String, DeliveryAgent> agentsMap;
    Map<String, List<DeliveryAgent>> zipToAgents;

    public AgentRepository() {
        agentsMap = new HashMap<>();
        zipToAgents = new HashMap<>();
    }

    public void save(DeliveryAgent agent) {
        agentsMap.put(agent.agentId, agent);
        for(String zip: agent.serviceableZipcodes) {
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

package design_problems.personal.due.amazon_locker.model;

import java.util.List;

public class DeliveryAgent {
    private String agentId;
    private String name;
    private List<String> serviceableZipcodes;

    public String getName() {
        return name;
    }

    public List<String> getServiceableZipcodes() {
        return serviceableZipcodes;
    }

    public String getAgentId() {
        return agentId;
    }
}

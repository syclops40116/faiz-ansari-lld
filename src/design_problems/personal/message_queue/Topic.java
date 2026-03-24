package design_problems.personal.message_queue;

public class Topic {

    private final String name;
    private final MessageQueue messageQueue;

    public Topic(String name) {
        this.name = name;
        this.messageQueue = new MessageQueue();
    }

    public String getName() {
        return name;
    }

    public MessageQueue getQueue() {
        return messageQueue;
    }
}

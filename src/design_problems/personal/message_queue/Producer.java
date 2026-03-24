package design_problems.personal.message_queue;

import java.util.UUID;

public class Producer {

    private QueueManager manager;

    public Producer() {
        this.manager = QueueManager.getInstance();
    }

    public void send(String topicName, String data) {

        Topic topic = manager.getTopic(topicName);

        if (topic == null) {
            throw new RuntimeException("Topic not found");
        }

        Message msg =
                new Message(UUID.randomUUID().toString(), data);

        topic.getQueue().publish(msg);
    }
}
package design_problems.personal.message_queue;

public class Message {

    private final String id;
    private final String payload;
    private final long timestamp;

    public Message(String id, String payload) {
        this.id = id;
        this.payload = payload;
        this.timestamp = System.currentTimeMillis();
    }

    public String getPayload() {
        return payload;
    }
}

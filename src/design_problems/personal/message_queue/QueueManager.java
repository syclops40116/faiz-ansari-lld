package design_problems.personal.message_queue;

import java.util.concurrent.ConcurrentHashMap;

public class QueueManager {

    private static QueueManager instance;

    private ConcurrentHashMap<String, Topic> topics =
            new ConcurrentHashMap<>();

    private QueueManager() {}

    public static synchronized QueueManager getInstance() {

        if (instance == null) {
            instance = new QueueManager();
        }
        return instance;
    }

    public void createTopic(String name) {
        topics.putIfAbsent(name, new Topic(name));
    }

    public Topic getTopic(String name) {
        return topics.get(name);
    }
}


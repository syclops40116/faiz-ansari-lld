package design_problems.personal.message_queue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class MessageQueue {

    private BlockingQueue<Message> queue =
            new LinkedBlockingQueue<>();

    public void publish(Message msg) {
        queue.offer(msg);
    }

    public Message consume() throws InterruptedException {
        return queue.take(); // blocks if empty
    }
}

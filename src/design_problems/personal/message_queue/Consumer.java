package design_problems.personal.message_queue;

public class Consumer implements Runnable {

    private String name;
    private Topic topic;

    public Consumer(String name, String topicName) {

        this.name = name;

        QueueManager manager =
                QueueManager.getInstance();

        this.topic = manager.getTopic(topicName);
    }

    @Override
    public void run() {

        try {

            while (true) {

                Message msg =
                        topic.getQueue().consume();

                process(msg);
            }

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void process(Message msg) {

        System.out.println(
                name + " consumed: " + msg.getPayload()
        );
    }
}


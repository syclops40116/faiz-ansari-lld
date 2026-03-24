package design_problems.personal.message_queue;

public class Main {

    public static void main(String[] args) {

        QueueManager manager =
                QueueManager.getInstance();

        // Create topic
        manager.createTopic("orders");

        // Producer
        Producer producer = new Producer();

        // Consumers
        Consumer c1 = new Consumer("C1", "orders");
        Consumer c2 = new Consumer("C2", "orders");

        new Thread(c1).start();
        new Thread(c2).start();

        // Send messages
        for (int i = 1; i <= 10; i++) {

            producer.send(
                    "orders",
                    "Order-" + i
            );
        }
    }
}

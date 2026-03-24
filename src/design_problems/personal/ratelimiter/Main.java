package design_problems.personal.ratelimiter;

import design_problems.personal.ratelimiter.constants.UserTier;
import design_problems.personal.ratelimiter.model.User;
import design_problems.personal.ratelimiter.service.RateLimiterService;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void makeConcurrentRequests(
            RateLimiterService rateLimiterService,
            User user,
            int totalRequests,
            int threadCount
    ) throws InterruptedException {

        ExecutorService executor = Executors.newFixedThreadPool(threadCount);
        CountDownLatch latch = new CountDownLatch(totalRequests);

        for (int i = 1; i <= totalRequests; i++) {
            int requestId = i;

            executor.submit(() -> {
                try {
                    boolean allowed = rateLimiterService.allowRequest(user);
                    System.out.println(
                            "Request " + requestId + " -> " +
                                    (allowed ? "ALLOWED" : "REJECTED") +
                                    " | Thread: " + Thread.currentThread().getName()
                    );
                } finally {
                    latch.countDown();
                }
            });
        }

        latch.await();      // wait for all tasks to finish
        executor.shutdown();
    }

    public static void main(String[] args) throws InterruptedException {
        RateLimiterService rateLimiterService = new RateLimiterService();

        User freeUser = new User("freeUser", UserTier.STANDARD);
        User premiumUser = new User("premiumUser", UserTier.PREMIUM);

//        makeConcurrentRequests(rateLimiterService, premiumUser, 10, 3);
//
//        Thread.sleep(6000);
//
//        makeConcurrentRequests(rateLimiterService, premiumUser, 10, 3);

        for(int i = 1; i <= 5; i++) {
            boolean allowed = rateLimiterService.allowRequest(premiumUser);
            System.out.println("Request " + i + (allowed ? " : ALLOWED" : " : REJECTED" ));
            Thread.sleep(100);
        }
    }
}
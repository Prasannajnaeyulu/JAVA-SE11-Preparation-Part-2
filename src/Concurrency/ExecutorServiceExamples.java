package Concurrency;

import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ExecutorServiceExamples {
    public static void main(String... args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(ExecutorServiceExamples::doSomething);

        // interesting: Note: newSingleThreadExecutor is a sequential single thread so it will run one thread at a time
        // hence this second execute task will not start until the first one above finishes
        executorService.execute(() -> {
            System.out.println("Executing second Thread");
            // Here the Type Integer for ArrayList<> is inferred from List.of method
            List<?> ls = new ArrayList<>(List.of(10, 20, 30));
            ls.forEach(System.out::print);
        });
        executorService.shutdown();

        ExecutorService executorService1 = Executors.newSingleThreadExecutor();
        Future<?> futuretask = executorService1.submit(() -> {
            System.out.println("Executor Service submit method...");
            Stream.iterate(1, t -> t+5).forEach(value -> {
                    System.out.println(value);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        });

        System.out.println("Is future task finished? : "+ futuretask.isDone());
        if(!futuretask.isDone()) {
            System.out.println("Canceling future task");
            System.out.println(futuretask.cancel(true));
        }

//        executorService1.shutdownNow();
//        executorService1.awaitTermination(3000, TimeUnit.SECONDS);

        // interesting: Note: this example invokes callable type because the lambda function returning the result
        // the one that doesn't return any result is a Runnable type
        Future<DoubleSummaryStatistics> dstats = executorService1.submit(() -> Stream.generate(Math::random).limit(20).collect(Collectors.summarizingDouble(s -> s)));
        System.out.println(dstats.get());

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        executorService1.shutdownNow();
    }

    private static void doSomething(){
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Executing thread 1");
    }
}

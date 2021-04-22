package Concurrency;

import java.sql.CallableStatement;
import java.sql.DriverManager;
import java.util.IntSummaryStatistics;
import java.util.concurrent.*;
import java.util.stream.Stream;

public class ExecutorSchedulers {
    public static void main(String... args) throws InterruptedException {
        Callable<IntSummaryStatistics> task = () -> ExecutorSchedulers.doSomething(1,300);
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
        ScheduledFuture<IntSummaryStatistics> scheduledFuture = scheduler.schedule(task, 2, TimeUnit.SECONDS);
        scheduler.awaitTermination(5, TimeUnit.SECONDS);
        if(scheduledFuture.isDone()) {
            try {
                System.out.println(scheduledFuture.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }

        }
        scheduler.shutdownNow();
    }

    public static IntSummaryStatistics doSomething(final int seed, final int limit){
        return Stream.iterate(seed, t -> t + 5)
                .limit(limit)
                // Note: the s here is of type java.lang.Integer
                .mapToInt(s -> s)
                .summaryStatistics();
    }
}

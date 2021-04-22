package Concurrency;

import java.util.Collection;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Stream;

public class ExecutorThreadPool {
    public static void main(String... args){
        Collection<Callable<IntSummaryStatistics>> tasks = List.of(
                () -> ExecutorThreadPool.doSomething(1, 10000),
                () -> ExecutorThreadPool.doSomething(2, 101),
                () -> ExecutorThreadPool.doSomething(3, 102));

//        ExecutorService  service = Executors.newCachedThreadPool();
//        ExecutorService service = Executors.newFixedThreadPool(2);
        ScheduledExecutorService service = Executors.newScheduledThreadPool(5);

        List<Future<IntSummaryStatistics>> lsResults = null;
        try {
            lsResults = service.invokeAll(tasks, 2, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        finally {
            service.shutdownNow();
            if(lsResults!=null){
                System.out.println("Results are: ");
                lsResults.forEach(futuretask -> {
                    try {
                        System.out.println(futuretask.get());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                });
            }
        }
    }

    public static IntSummaryStatistics doSomething(int seed, int limit){
        return Stream.iterate(seed, t -> t + 5)
                .limit(limit)
                .mapToInt(Integer::intValue)
                .summaryStatistics();
    }
}

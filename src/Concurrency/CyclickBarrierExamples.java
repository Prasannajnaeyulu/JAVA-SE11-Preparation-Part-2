package Concurrency;

import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CyclickBarrierExamples {
    public static void main(String... args){
        CyclicBarrier barrier = new CyclicBarrier(4, () -> {
            System.out.println("Cyclic barrier is now complete");
        });

        Callable<String> task = () -> {
            // Here all threads running step 1 runs step 2 as well in parallel
            // If we want this behavior to be different i.e., If I want to run step1 for all threads first
            // and then proceed to step2, we can use CyclicBarrier here.
            // Imagine CyclicBarrier is like a toll gate. When all the threads are doing the same task i.e., driving on Toll roads
            // CyclicBarrier will block the cars from proceeding to further journey until the CyclicBarrier releases them
            step(1);
            // It blocks the current thread from proceeding with step2
            // It releases all the blocked threads at once whenever the cyclicBarrier reaches a required count of parties it's configured with
            // here in out example I configured CyclicBarrier with 4 parties . Excerpts CyclicBarrier barrier = new CyclicBarrier(4...
            barrier.await();
            step(2);
            return "Completed";
        };

        ExecutorService service = Executors.newFixedThreadPool(4);
//        Runnable r = () -> System.out.println("hello");
        // compiler error: there is no invokeAll method which takes Collection<Runnable> instance
        // it must be Collection<Callable) instance
//        service.invokeAll(List.of(r, r,r,r));

        try {
            // Note: It must be Collection<Callable> instance
            service.invokeAll(List.of(task, task, task, task));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        service.shutdownNow();
    }

    public static void step(int number){
        int ms = new Random().nextInt(5)* 1000;
        System.out.println(" Thread: "+ Thread.currentThread().getName()+ " is waiting for: "+ ms+ " milliseconds");
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+ " Step: "+ number+" is completed");
    }
}

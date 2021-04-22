package Concurrency.Synchronization;/*
The Learn Programming Academy
Java SE 11 Developer 1Z0-819 OCP Course - Part 2
Section 11: Concurrency
Topic:  Shared data approaches
*/

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// This class represents a ball which contains information
// about who hit the ball and how many times each player hit it
class TheBallInTheAir {

    private String ballType = "Volleyball";
    // Total number of hits on the ball

    private AtomicInteger hit = new AtomicInteger(0);
    // Map of players and their hits..
    private Map<String, Integer> players = Collections.synchronizedMap(new TreeMap<>());

    // Constructor
    TheBallInTheAir() {
        players.put("Jane", 0);
        players.put("Mary", 0);
        players.put("Ralph", 0);
        players.put("Joe", 0);
    }

    // Method called by threads, adds player to map if not found
    // or increments value in map
    public int addHit(String player) {

        if (players.containsKey(player)) {
            players.computeIfPresent(player, (key, val) -> ++val);
        } else {
            // A null is returned if no mapping was found
            if (players.putIfAbsent(player, 1) != null) {
                System.out.println("Encountered race condition");
                players.computeIfPresent(player, (key, val) -> ++val);
            }

        }
        return hit.incrementAndGet();
    }

    // Present writeable output
    public String toString() {
        return "Total " + ballType + " hits: " + this.hit.get()
                + ", Player hits: " + players.values().stream().mapToInt(s -> s).sum()
                + "\nPlayers List: " + players;
    }
}

public class SynchronizedMethod {

    // Create a shared ball.
    public static TheBallInTheAir sharedBall = new TheBallInTheAir();

    public static void main(String[] args) {
        // Set up players
        String[] players = {"Jane", "Mary", "Ralph", "Joe"};
        Random r = new Random();
        // random list of player names, representing their turn to hit ball
        List<String> playerTurns =
                Stream.generate(() -> players[r.nextInt(4)])
                        .limit(100)
                        .collect(Collectors.toList());

        ExecutorService executorService = null;

        try {
            executorService = Executors.newFixedThreadPool(5);

            // Start the volleyball game...
            for (String player : playerTurns) {
                executorService.submit(() -> sharedBall.addHit(player));
            }

        } finally {
            if (executorService != null) {
                executorService.shutdown();
                try {
                    // Wait no longer than 1 second for completion confirmation
                    executorService.awaitTermination(1, TimeUnit.SECONDS);
                    System.out.println(sharedBall);

                } catch (InterruptedException ie) {
                    ie.printStackTrace();
                }
            }
        }
    }
}
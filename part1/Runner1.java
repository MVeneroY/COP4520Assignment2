import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

// N upper bound - 100

class Runner1 {
    static int runs;

    static boolean cupcake[] = {true};
    static Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        int n = 0;
        if (args.length == 1) n = Integer.parseInt(args[0]);
        else n = 8;

        if (n > 100) {
            System.out.println("N upper bound (100) exceeded.");
            System.exit(1);
        }
        System.out.println(n + " threads started");

        long startTime = System.currentTimeMillis();
        int cupcakes[] = {n - 1};
        runs = n-1;
        boolean allVisited[] = {false};

        ExecutorService executorService = Executors.newFixedThreadPool(n);
        Runnable guests[] = new Guest1[n];
        for (int i = 0; i < n; ++i) {
            guests[i] = new Guest1(lock, cupcake, cupcakes, allVisited);
        }

        Random rand = new Random();
        for (int j = 0; j < runs; ++j) {
            int index = rand.nextInt(n);
            System.out.println("run: " + (j+1) + ". id: " + index);
            executorService.execute(guests[index]);
        }

        if (allVisited[0]) System.out.println("All guests visited the labirynth at least once");
        else System.out.println("Not all guests visited the labyrinth");
        executorService.shutdown();

        while (!executorService.isTerminated()) { 
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }

        long endTime = System.currentTimeMillis();
        System.out.println((endTime - startTime) + " ms");
    }
}
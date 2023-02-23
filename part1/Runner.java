import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

// N upper bound - 100

class Runner {
    static int runs = 20;

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

        // System.out.println(cupcake);
        // eatCupcake();
        // System.out.println(cupcake);
        // replenishCupcake();
        // System.out.println(cupcake);

        ExecutorService executorService = Executors.newFixedThreadPool(n);
        Runnable guests[] = new Guest[n];
        for (int i = 0; i < n; ++i) {
            guests[i] = new Guest(i, lock, cupcake);
        }

        Random rand = new Random();
        for (int j = 0; j < runs; ++j) {
            System.out.println("run: " + (j+1));
            int index = rand.nextInt(n);
            executorService.execute(guests[index]);
        }
        executorService.shutdown();

        while (!executorService.isTerminated()) { 
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }

        System.out.println(cupcake[0]);
    }
}
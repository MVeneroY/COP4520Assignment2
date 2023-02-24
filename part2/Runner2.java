import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Runner2 {

    static Guest2[] q;
    static int[] vase = {0};
    static int guests = 8;
    static int done = 0;

    static Random rand = new Random();

    public static void main(String[] args) {
        
        // Initialize threads and place them in queue
        q = new Guest2[guests];
        ExecutorService executorService = Executors.newFixedThreadPool(guests);
        for (int i = 0; i < guests; ++i) {
            q[i] = new Guest2(q, i, vase);
        }

        // Let guests into the room as per the queue
        int index = 0;
        while (done != guests) {
            if (!q[index % guests].done) {
                if (index != 0) while (!q[(index - 1) % guests].proceed) { 
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        System.out.println(e);
                    }
                }

                executorService.execute(q[index % guests]);

                // Guests may go back into the queue
                if (rand.nextDouble() > 0.5d) {
                    q[index % guests].done = true;
                    done += 1;
                }
            }
            
            index += 1;
        }        

        // terminate threads
        executorService.shutdown();
        while (!executorService.isTerminated()) { 
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }
    }
}

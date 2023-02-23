import java.util.concurrent.locks.Lock;

public class Guest implements Runnable {

    private Lock lock;
    private boolean[] cupcake;
    private int[] cupcakes;
    private boolean hadCupcake = false;
    private boolean[] allVisited;

    public Guest(Lock lock, boolean[] cupcake, int[] cupcakes, boolean[] allVisited) { 
        this.lock = lock; 
        this.cupcake = cupcake;
        this.cupcakes = cupcakes;
        this.allVisited = allVisited;
    }
    
    @Override
    public void run() {

        // Guest will eat cupcake only the first time they visit the labyrinth
        if (lock.tryLock()) {
            if (!cupcake[0]) replenishCupcake();
            if (!this.hadCupcake) eatCupcake();
            lock.unlock();
        }
    }

    void eatCupcake() {
        lock.lock();
        try {
            cupcake[0] = false;
            this.hadCupcake = true;
        } finally {
            lock.unlock();
        }
    }

    void replenishCupcake() {
        lock.lock();
        try {
            if (this.cupcakes[0] == 0) allVisited[0] = true;
            cupcake[0] = true;
            cupcakes[0] -= 1;
        } finally {
            lock.unlock();
        }
    }
}

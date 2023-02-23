import java.util.concurrent.locks.Lock;

public class Guest implements Runnable {

    private Lock lock;
    private boolean[] cupcake;
    private boolean hasRun = false;
    private int index = -1;

    public Guest(int index, Lock lock, boolean[] cupcake) { 
        this.index = index;
        this.lock = lock; 
        this.cupcake = cupcake;
    }
    
    @Override
    public void run() {

        // System.out.println("Testing");
        if (lock.tryLock()) {
            if (cupcake[0]) eatCupcake();
            else replenishCupcake();
            lock.unlock();
        }

        hasRun = true;
        getHasRun();
    }

    void eatCupcake() {
        lock.lock();
        try {
            cupcake[0] = false;
        } finally {
            lock.unlock();
        }
    }

    void replenishCupcake() {
        lock.lock();
        try {
            cupcake[0] = true;
        } finally {
            lock.unlock();
        }
    }

    void getHasRun() {
        System.out.println(this.index + ": " + this.hasRun);
    }
}

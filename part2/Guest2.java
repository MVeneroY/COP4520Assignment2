public class Guest2 implements Runnable {

    Guest2[] q;
    int[] vase;
    int id = -1;
    boolean proceed = false;
    boolean done = false;

    Guest2(Guest2[] q, int id, int[] vase) {
        this.q = q;
        this.id = id;
        this.vase = vase;
    }

    @Override
    public void run() {
        seeVase();
    }

    synchronized void seeVase() {
        proceed = false;

        // Guest sees vase an accidentally changes its value
        this.vase[0] += 1;       
        System.out.println("Thread " + id + " vase counter: " + this.vase[0]);

        // Guest notifies next in queue that they may go in
        proceed = true;
    }
}

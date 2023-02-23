public class Guest implements Runnable {

    Guest[] q;
    int id = -1;
    boolean proceed = false;
    boolean done = false;

    Guest(Guest[] q, int id) {
        this.q = q;
        this.id = id;
    }

    @Override
    public void run() {
        proceed = false;
        System.out.println("Thread "+ id);
        proceed = true;
        System.out.println(id + " is ready to proceed");
    }
}

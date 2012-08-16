import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author liubida 2012-2-15 下午5:10:33
 */
class Meal {

    int num;

    Meal(int num){
        this.num = num;
    }

    public String toString() {
        return "Meal: " + num;
    }
}

public class Restaurant {

    int             max  = 10;
    boolean         flag = true;
    ExecutorService exec = Executors.newCachedThreadPool();
    Meal            meal = new Meal(0);
    Chef            c    = new Chef(this);
    WaitPerson      w    = new WaitPerson(this);

    public Restaurant(){
        exec.execute(c);
        exec.execute(w);
    }

    public static void main(String[] args) {
        new Restaurant();
    }

    class Chef implements Runnable {

        int                count = 0;

        private Restaurant r;

        Chef(Restaurant r){
            this.r = r;
        }

        public void run() {
            try {
                while (flag && !Thread.interrupted()) {
                    synchronized (r.c) {
                        if (null != meal) {
                            wait();
                        }
                    }
                    synchronized (r.w) {
                        if (count >= max) {
                            flag = false;
                        } else {
                            meal = new Meal(count++);
                            System.out.println("cook: " + meal);
                            r.w.notifyAll();
                        }
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    class WaitPerson implements Runnable {

        private Restaurant r;

        WaitPerson(Restaurant r){
            this.r = r;
        }

        public void run() {
            try {
                while (flag && !Thread.interrupted()) {
                    synchronized (r.w) {
                        if (null == meal) {
                            wait();
                        }
                    }
                    synchronized (r.c) {
                        System.out.println("eat: " + meal);
                        meal = null;
                        r.c.notifyAll();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

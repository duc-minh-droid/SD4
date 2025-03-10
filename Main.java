import java.util.LinkedList;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        final PC pc = new PC();
        
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    pc.produce();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    pc.consume();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        t1.start();
        t2.start();
        
        t1.join();
        t2.join();
    }

    public static class PC {
        LinkedList<Integer> list = new LinkedList<>();
        int capacity = 2;

        public void produce() throws InterruptedException {
            synchronized(this) {
                while (list.size() == capacity) {
                    wait();
                }
                list.add(0 );
                notify();
            }
        }

        public void consume() throws InterruptedException {
            synchronized(this) {
                while (list.size() == 0) {
                    wait();
                }
                list.removeLast();
                notify();
            }
        }
    }
}

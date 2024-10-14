import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;

public class ThreadPoolApp {
    private static void error() {
        System.out.println("ThreadPoolApp must be run with two positive valued " +
                "integer arguments. The first detailing the number of jobs " +
                "the second the number of processing threads in the pool");
        System.exit(0); // Exits the program
    }

    public static void main(String[] args) {
        if (args.length < 2)
            ThreadPoolApp.error();
        try {
            int numberOfJobs = Integer.parseInt(args[0]);
            int numberOfThreads = Integer.parseInt(args[1]);
            if ((numberOfJobs < 1) || (numberOfThreads < 1))
                ThreadPoolApp.error();
            ExecutorService pool = Executors.newFixedThreadPool(numberOfThreads);
            Job[] jobs = new Job[numberOfJobs];
            for (int i = 0; i < numberOfJobs; i++) {
                jobs[i] = new Job(i);
                pool.execute(jobs[i]); // Executes the command at a future time.
            }
            pool.shutdown(); // Ensures previously submitted tasks are executed.
            System.out.println("Last line " + Thread.currentThread().getName());
        } catch (NumberFormatException e) {
            ThreadPoolApp.error();
        }
    }
}

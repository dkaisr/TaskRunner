package de.dkaisr.taskrunner.demo;

import de.dkaisr.taskrunner.Task;
import de.dkaisr.taskrunner.TaskRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ConcurrentTaskRunner implements TaskRunner {
    private final List<Task<?, ?>> tasks = new ArrayList<>();
    private final ExecutorService executor;

    public ConcurrentTaskRunner(int threads) {
        executor = Executors.newFixedThreadPool(threads);
    }

    @Override
    public void submit(Task<?, ?> task) {
        tasks.add(task);
    }

    @SuppressWarnings("OptionalGetWithoutIsPresent") // on success, result can't be empty, same for error on failure
    @Override
    public void runAll() {
        List<Future<?>> futures = new ArrayList<>();
        for (Task<?, ?> task : tasks){
            IO.println("Submitting Task " + task.getId() + " to executor.");
            futures.add(executor.submit(task::run));
        }

        for (Future<?> f : futures) {
            try {
                f.get();
            } catch (Exception e) {
                // Tasks handle exceptions internally
            }
        }
        executor.shutdown();

        for (Task<?, ?> task : tasks) {
            if (task.hasFailed()) {
                IO.println("Task " + task.getId() + " has failed.");
                IO.println("Error: " + task.getError().get());
            } else if (task.hasSucceeded()) {
                IO.println("Task " + task.getId() + " has succeeded.");
                IO.println("Result: " + task.getResult().get());
            }
        }
    }
}

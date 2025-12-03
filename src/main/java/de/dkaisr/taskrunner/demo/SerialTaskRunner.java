package de.dkaisr.taskrunner.demo;

import de.dkaisr.taskrunner.Task;
import de.dkaisr.taskrunner.TaskRunner;

import java.util.LinkedList;
import java.util.Queue;

public class SerialTaskRunner implements TaskRunner {
    private final Queue<Task<?, ?>> queue = new LinkedList<>();

    @Override
    public void submit(Task<?, ?> task) {
        queue.add(task);
    }

    @SuppressWarnings("OptionalGetWithoutIsPresent") // on success, result can't be empty, same for error on failure
    @Override
    public void runAll() {
        while (!queue.isEmpty()) {
            Task<?, ?> task = queue.poll();
            IO.println("Running Task " + task.getId());
            task.run();
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

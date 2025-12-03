package de.dkaisr.taskrunner;

public interface TaskRunner {
    void submit(Task<?, ?> task);
    void runAll();
}

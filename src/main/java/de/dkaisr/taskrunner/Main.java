package de.dkaisr.taskrunner;

import de.dkaisr.taskrunner.demo.ConcurrentTaskRunner;
import de.dkaisr.taskrunner.demo.FactorialStrategy;
import de.dkaisr.taskrunner.demo.SerialTaskRunner;
import de.dkaisr.taskrunner.demo.StringReverseTask;

public class Main {
    private static final String[] STRINGS = {"abc", "Hello World", null, "This is a sentence."};
    private static final int[] NUMBERS = { 5, 7, 11, -4, 3};

    static void main() {
        TaskRunner serial_runner = new SerialTaskRunner();
        TaskRunner concurrent_runner = new ConcurrentTaskRunner(4);
        for (String s : STRINGS) {
            serial_runner.submit(new StringReverseTask(s));
            concurrent_runner.submit(new StringReverseTask(s));
        }
        // Example of strategy without special Task implementation
        FactorialStrategy factorialStrategy = new FactorialStrategy();
        for (int n : NUMBERS) {
            serial_runner.submit(new Task<>(factorialStrategy, n));
            concurrent_runner.submit(new Task<>(factorialStrategy, n));
        }
        IO.println("Serial Runner:");
        serial_runner.runAll();
        IO.println("\nConcurrent Runner:");
        concurrent_runner.runAll();
    }
}
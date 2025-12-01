package de.dkaisr.taskrunner;

import de.dkaisr.taskrunner.demo.StringReverseTask;

public class Main {
    static void main() {
        StringReverseTask task = new StringReverseTask("Hello world");
        task.run();
        if (task.hasFinished()) {
            IO.println(task.getResult().orElse("Task failed."));
        }
    }
}
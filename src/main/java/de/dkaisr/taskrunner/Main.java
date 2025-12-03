package de.dkaisr.taskrunner;

import de.dkaisr.taskrunner.demo.SerialTaskRunner;
import de.dkaisr.taskrunner.demo.StringReverseTask;

public class Main {
    private static final String[] STRINGS = {"abc", "Hello World", null, "This is a sentence."};

    static void main() {
        TaskRunner runner = new SerialTaskRunner();
        for (String s : STRINGS) {
            runner.submit(new StringReverseTask(s));
        }
        runner.runAll();
    }
}
package de.dkaisr.taskrunner.demo;

import de.dkaisr.taskrunner.Task;

public class StringReverseTask extends Task<String, String> {
    public StringReverseTask(String data) {
        StringReverseStrategy strategy = new StringReverseStrategy();
        super(strategy, data);
    }
}

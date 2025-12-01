package de.dkaisr.taskrunner.demo;

import de.dkaisr.taskrunner.Strategy;

public class StringReverseStrategy implements Strategy<String, String> {

    @Override
    public String execute(String data) throws IllegalArgumentException {
        if (data == null) {
            throw new IllegalArgumentException("String can't be null");
        }
        return new StringBuilder(data).reverse().toString();
    }
}

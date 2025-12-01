package de.dkaisr.taskrunner;

public interface Strategy<T, R> {
    R execute(T data);
}

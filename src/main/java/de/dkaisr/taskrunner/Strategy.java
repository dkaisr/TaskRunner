package de.dkaisr.taskrunner;

public interface Strategy<T, R> {
    Result<R> execute(T data);
}

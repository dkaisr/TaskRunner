package de.dkaisr.taskrunner;

import java.util.Objects;
import java.util.Optional;

@SuppressWarnings("ClassCanBeRecord")
public class Result<T> {
    private final T value;
    private final Status status;

    private Result(T value, Status status) {
        this.value = value;
        this.status = status;
    }

    public static <T> Result<T> waiting() {
        return new Result<>(null, Status.WAITING);
    }

    public static <T> Result<T> running() {
        return new Result<>(null, Status.RUNNING);
    }

    public static <T> Result<T> failed(Throwable error) {
        return new Result<>(null, Status.FAILED);
    }

    public static <T> Result<T> ok(T value) {
        Objects.requireNonNull(value, "OK result cannot wrap null");
        return new Result<>(value, Status.OK);
    }

    public Status getStatus() {
        return this.status;
    }

    public boolean isWaiting() {
        return this.status == Status.WAITING;
    }

    public boolean isRunning() {
        return this.status == Status.RUNNING;
    }

    public boolean hasFailed() {
        return this.status == Status.FAILED;
    }

    public boolean isOk() {
        return this.status == Status.OK;
    }

    public Optional<T> getValue() {
        return isOk() ? Optional.of(value) : Optional.empty();
    }
}

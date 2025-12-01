package de.dkaisr.taskrunner;

import java.util.Optional;
import java.util.UUID;

public class Task<T, R> {
    private final UUID id;
    private Strategy<T, R> strategy;
    private T data;
    private Status status;
    private R result;

    public Task(Strategy<T, R> strategy, T data) {
        this.id = UUID.randomUUID();
        this.strategy = strategy;
        this.data = data;
        this.status = Status.WAITING;
        this.result = null;
    }

    public void run() {
        status = Status.RUNNING;
        try {
            result = strategy.execute(this.data);
            status = Status.SUCCESS;
        } catch (Exception e) {
            status = Status.FAILED;
        }
    }

    public UUID getId() {
        return id;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Strategy<T, R> getStrategy() {
        return strategy;
    }

    public void setStrategy(Strategy<T, R> strategy) {
        this.strategy = strategy;
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

    public boolean hasSucceeded() {
        return this.status == Status.SUCCESS;
    }

    public boolean hasFinished() {
        return hasFailed() || hasSucceeded();
    }

    public Optional<R> getResult() {
        return hasSucceeded() ? Optional.ofNullable(result) : Optional.empty();
    }

}

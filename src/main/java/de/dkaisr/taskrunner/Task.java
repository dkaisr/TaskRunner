package de.dkaisr.taskrunner;

import java.util.UUID;

public class Task<T, R> {
    private final UUID id;
    private T data;
    private Strategy<T, R> strategy;
    private Result<R> result;

    public Task(Strategy<T, R> strategy, T data) {
        this.id = UUID.randomUUID();
        this.strategy = strategy;
        this.data = data;
        this.result = Result.waiting();
    }

    public void run() {
        result = strategy.execute(this.data);
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

    public Result<R> getResult() {
        return this.result;
    }

    public Status getStatus() {
        return this.result.getStatus();
    }

    public boolean isFinished() {
        return this.result.isOk() || this.result.hasFailed();
    }

}

package com.jmperezra.foodie.commons.invoker;

import com.presentation.UseCaseExecution;
import com.presentation.UseCaseInvoker;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

import javax.inject.Inject;

public class UseCaseInvokerImp implements UseCaseInvoker {

    private ExecutorService executor;
    private Thread.UncaughtExceptionHandler uncaughtExceptionHandler;

    @Inject
    public UseCaseInvokerImp(ExecutorService executor, Thread.UncaughtExceptionHandler uncaughtExceptionHandler) {
        this.executor = executor;
        this.uncaughtExceptionHandler = uncaughtExceptionHandler;
    }

    @Override public <T> Future<T> execute(UseCaseExecution<T> execution) {
        if (execution.getUseCaseResult() != null) {
            return (Future<T>) executor.submit(new UseCaseExecutionFutureTask<>(execution, uncaughtExceptionHandler));
        } else {
            return executor.submit(new PriorityUseCaseDecorator<>(execution));
        }
    }
}

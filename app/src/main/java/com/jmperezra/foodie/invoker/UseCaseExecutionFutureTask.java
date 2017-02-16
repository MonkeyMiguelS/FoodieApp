package com.jmperezra.foodie.invoker;

//import com.mo2o.ruralvia.domain.usecase.UseCaseError;
//import com.mo2o.ruralvia.domain.usecase.UseCaseResponse;
//import com.mo2o.ruralvia.presentation.modules.UseCaseExecution;
//import com.mo2o.ruralvia.presentation.modules.UseCaseResult;

import com.domain.usecase.UseCaseError;
import com.domain.usecase.UseCaseResponse;
import com.presentation.UseCaseExecution;
import com.presentation.UseCaseResult;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class UseCaseExecutionFutureTask<T> extends FutureTask<T> implements PriorizableUseCase {

    private final UseCaseExecution<T> useCaseExecution;
    private final Thread.UncaughtExceptionHandler uncaughtExceptionHandler;
    private final String description;

    public UseCaseExecutionFutureTask(UseCaseExecution<T> useCaseExecution,
                                      Thread.UncaughtExceptionHandler uncaughtExceptionHandler) {
        super((Callable<T>) useCaseExecution.getUseCase());
        this.useCaseExecution = useCaseExecution;
        this.uncaughtExceptionHandler = uncaughtExceptionHandler;
        this.description = useCaseExecution.getUseCase().getClass().toString();
    }

    @Override protected void done() {
        super.done();
        try {
            handleResponse((UseCaseResponse<T>) get());
        } catch (Exception e) {
            handleException(e);
        }
    }

    private void handleException(Exception e) {
        Throwable causeException = e.getCause();
        unhandledException(causeException != null ? causeException : e);
    }

    private void handleResponse(UseCaseResponse<T> response) {
        if (response.hasError()) {
            handleError(response.getError());
        } else {
            handleResult(response.getResult());
        }
    }

    private void handleResult(T result) {
        useCaseExecution.getUseCaseResult().onResult(result);
    }

    private void handleError(UseCaseError error) {
        UseCaseResult errorResult = useCaseExecution.getInteractorErrorResult(error.getClass());
        if (errorResult != null) {
            errorResult.onResult(error);
        } else {
            unhandledException(new RuntimeException("Unhandled handleError action: " + error.getClass().toString()));
        }
    }

    private void unhandledException(Throwable cause) {
        UnhandledUseCaseException e = new UnhandledUseCaseException(description, cause.getClass().getName());
        e.initCause(cause);
        uncaughtExceptionHandler.uncaughtException(Thread.currentThread(), e);
    }

    public int getPriority() {
        return useCaseExecution.getPriority();
    }

    @Override public String getDescription() {
        return description;
    }
}

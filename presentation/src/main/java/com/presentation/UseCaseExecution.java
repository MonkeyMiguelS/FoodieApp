package com.presentation;

import com.domain.usecase.UseCase;
import com.domain.usecase.UseCaseError;
import com.domain.usecase.UseCaseResponse;

import java.util.HashMap;
import java.util.Map;


public class UseCaseExecution<T> {
    private UseCaseResult<T> useCaseResult;
    private final Map<Class<? extends UseCaseError>, UseCaseResult<? extends UseCaseError>> errors = new HashMap<>(0);
    private final UseCase<UseCaseResponse<T>> useCase;
    private int priority;

    public UseCaseExecution(UseCase<UseCaseResponse<T>> useCase) {
        this.useCase = useCase;
    }

    public UseCaseExecution<T> result(UseCaseResult<T> useCaseResult) {
        this.useCaseResult = useCaseResult;
        return this;
    }

    public UseCaseExecution<T> error(Class<? extends UseCaseError> errorClass,
                                     UseCaseResult<? extends UseCaseError> interactorError) {
        this.errors.put(errorClass, interactorError);
        return this;
    }

    public UseCaseExecution<T> priority(int priority) {
        this.priority = priority;
        return this;
    }

    public UseCase<UseCaseResponse<T>> getUseCase() {
        return useCase;
    }

    public UseCaseResult<? extends UseCaseError> getInteractorErrorResult(
            Class<? extends UseCaseError> errorClass) {
        return errors.get(errorClass);
    }

    public UseCaseResult<T> getUseCaseResult() {
        return useCaseResult;
    }

    public void execute(UseCaseInvoker useCaseInvoker) {
        useCaseInvoker.execute(this);
    }

    public int getPriority() {
        return priority;
    }
}

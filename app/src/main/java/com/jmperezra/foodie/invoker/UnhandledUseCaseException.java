package com.jmperezra.foodie.invoker;

public class UnhandledUseCaseException extends RuntimeException {

    public UnhandledUseCaseException(String interactorName, String initiatorException) {
        super(String.format("Your interactor %s does not handle the exception: %s", interactorName,
                initiatorException));
    }
}

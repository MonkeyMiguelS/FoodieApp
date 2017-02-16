package com.jmperezra.foodie.invoker;

public interface PriorizableUseCase {
    int getPriority();
    String getDescription();
}

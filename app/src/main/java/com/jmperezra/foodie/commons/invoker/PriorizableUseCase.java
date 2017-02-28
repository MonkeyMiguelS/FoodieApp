package com.jmperezra.foodie.commons.invoker;

public interface PriorizableUseCase {
    int getPriority();
    String getDescription();
}

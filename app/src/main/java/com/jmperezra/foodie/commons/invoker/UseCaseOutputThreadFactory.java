package com.jmperezra.foodie.commons.invoker;

import java.util.concurrent.ThreadFactory;

public class UseCaseOutputThreadFactory implements ThreadFactory {

    @Override public Thread newThread(Runnable r) {
        return new Thread(r, "UseCaseInvoker" + r.toString());
    }
}

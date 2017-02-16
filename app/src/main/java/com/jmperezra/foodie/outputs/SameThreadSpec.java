package com.jmperezra.foodie.outputs;

import me.panavtec.threaddecoratedview.views.ThreadSpec;

public class SameThreadSpec implements ThreadSpec {

    @Override public void execute(Runnable action) {
        action.run();
    }
}

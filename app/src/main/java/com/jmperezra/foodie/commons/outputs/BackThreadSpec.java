package com.jmperezra.foodie.commons.outputs;

import me.panavtec.threaddecoratedview.views.ThreadSpec;

public class BackThreadSpec implements ThreadSpec {

    @Override public void execute(Runnable action) {
        new Thread(action).start();
    }
}

/*
 * Decompiled with CFR 0_115.
 */
package com.google.common.util.concurrent;

import com.google.common.util.concurrent.AbstractService;
import com.google.common.util.concurrent.Monitor;
import com.google.common.util.concurrent.Monitor$Guard;
import com.google.common.util.concurrent.Service$State;

class AbstractService$5
extends Monitor$Guard {
    final /* synthetic */ AbstractService this$0;

    AbstractService$5(AbstractService abstractService, Monitor monitor) {
        this.this$0 = abstractService;
        super(monitor);
    }

    @Override
    public boolean isSatisfied() {
        if (this.this$0.state() != Service$State.NEW) return false;
        return true;
    }
}

/*
 * Decompiled with CFR 0_115.
 */
package com.google.common.util.concurrent;

import com.google.common.base.Supplier;
import java.util.concurrent.Semaphore;

final class Striped$4
implements Supplier {
    final /* synthetic */ int val$permits;

    Striped$4(int n2) {
        this.val$permits = n2;
    }

    @Override
    public Semaphore get() {
        return new Semaphore(this.val$permits, false);
    }
}


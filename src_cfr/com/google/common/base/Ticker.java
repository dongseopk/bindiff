/*
 * Decompiled with CFR 0_115.
 * 
 * Could not load the following classes:
 *  javax.annotation.CheckReturnValue
 */
package com.google.common.base;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Ticker$1;
import javax.annotation.CheckReturnValue;

@Beta
@GwtCompatible
public abstract class Ticker {
    private static final Ticker SYSTEM_TICKER = new Ticker$1();

    protected Ticker() {
    }

    public abstract long read();

    @CheckReturnValue
    public static Ticker systemTicker() {
        return SYSTEM_TICKER;
    }
}


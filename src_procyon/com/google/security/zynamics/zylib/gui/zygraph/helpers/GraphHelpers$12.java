package com.google.security.zynamics.zylib.gui.zygraph.helpers;

import com.google.security.zynamics.zylib.types.common.*;

final class GraphHelpers$12 implements INodeCallback
{
    final /* synthetic */ IFilteredItemCallback val$callback;
    
    GraphHelpers$12(final IFilteredItemCallback val$callback) {
        this.val$callback = val$callback;
    }
    
    @Override
    public IterationMode next(final Object o) {
        if (this.val$callback.qualifies(o)) {
            return this.val$callback.next(o);
        }
        return IterationMode.CONTINUE;
    }
}

package com.google.common.util.concurrent;

import com.google.common.util.concurrent.ListenerCallQueue$Callback;
import com.google.common.util.concurrent.Service$Listener;

final class AbstractService$2 extends ListenerCallQueue$Callback {
   AbstractService$2(String var1) {
      super(var1);
   }

   void call(Service$Listener var1) {
      var1.running();
   }
}

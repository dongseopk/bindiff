package com.google.common.util.concurrent;

import com.google.common.util.concurrent.Futures$2;

class Futures$2$1 implements Runnable {
   // $FF: synthetic field
   final Runnable val$command;
   // $FF: synthetic field
   final Futures$2 this$0;

   Futures$2$1(Futures$2 var1, Runnable var2) {
      this.this$0 = var1;
      this.val$command = var2;
   }

   public void run() {
      this.this$0.thrownFromDelegate = false;
      this.val$command.run();
   }
}

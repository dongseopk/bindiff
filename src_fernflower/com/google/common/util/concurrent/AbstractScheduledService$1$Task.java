package com.google.common.util.concurrent;

import com.google.common.util.concurrent.AbstractScheduledService;
import com.google.common.util.concurrent.AbstractScheduledService$1;
import java.util.logging.Level;

class AbstractScheduledService$1$Task implements Runnable {
   // $FF: synthetic field
   final AbstractScheduledService$1 this$1;

   AbstractScheduledService$1$Task(AbstractScheduledService$1 var1) {
      this.this$1 = var1;
   }

   public void run() {
      AbstractScheduledService$1.access$100(this.this$1).lock();

      try {
         if(AbstractScheduledService$1.access$200(this.this$1).isCancelled()) {
            return;
         }

         this.this$1.this$0.runOneIteration();
      } catch (Throwable var8) {
         try {
            this.this$1.this$0.shutDown();
         } catch (Exception var7) {
            AbstractScheduledService.access$300().log(Level.WARNING, "Error while attempting to shut down the service after failure.", var7);
         }

         this.this$1.notifyFailed(var8);
         AbstractScheduledService$1.access$200(this.this$1).cancel(false);
      } finally {
         AbstractScheduledService$1.access$100(this.this$1).unlock();
      }

   }
}

package com.google.security.zynamics.bindiff.gui.dialogs;

import com.google.security.zynamics.bindiff.gui.dialogs.ViewSearchOptionsDialog;
import com.google.security.zynamics.bindiff.gui.dialogs.ViewSearchOptionsDialog$1;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class ViewSearchOptionsDialog$InternalOkButtonListener implements ActionListener {
   // $FF: synthetic field
   final ViewSearchOptionsDialog this$0;

   private ViewSearchOptionsDialog$InternalOkButtonListener(ViewSearchOptionsDialog var1) {
      this.this$0 = var1;
   }

   public void actionPerformed(ActionEvent var1) {
      ViewSearchOptionsDialog.access$202(this.this$0, true);
      this.this$0.dispose();
   }

   // $FF: synthetic method
   ViewSearchOptionsDialog$InternalOkButtonListener(ViewSearchOptionsDialog var1, ViewSearchOptionsDialog$1 var2) {
      this(var1);
   }
}

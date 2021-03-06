package com.google.security.zynamics.bindiff.gui.dialogs.criteriadialog.expressiontree.actions;

import com.google.security.zynamics.bindiff.gui.dialogs.criteriadialog.expressiontree.ExpressionTreeActionProvider;
import com.google.security.zynamics.bindiff.gui.dialogs.criteriadialog.operators.NotCriterium;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

public class AppendNotOperatorAction extends AbstractAction {
   private final ExpressionTreeActionProvider actionProvider;

   public AppendNotOperatorAction(ExpressionTreeActionProvider var1) {
      super("Append NOT");
      this.actionProvider = var1;
   }

   public void actionPerformed(ActionEvent var1) {
      this.actionProvider.appendCriterium(new NotCriterium());
   }
}

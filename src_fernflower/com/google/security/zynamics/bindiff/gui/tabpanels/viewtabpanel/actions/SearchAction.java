package com.google.security.zynamics.bindiff.gui.tabpanels.viewtabpanel.actions;

import com.google.common.base.Preconditions;
import com.google.security.zynamics.bindiff.gui.tabpanels.viewtabpanel.ViewTabPanelFunctions;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

public class SearchAction extends AbstractAction {
   private final ViewTabPanelFunctions controller;

   public SearchAction(ViewTabPanelFunctions var1) {
      this.controller = (ViewTabPanelFunctions)Preconditions.checkNotNull(var1);
   }

   public void actionPerformed(ActionEvent var1) {
      this.controller.setCaretIntoSearchField();
   }
}
package com.google.security.zynamics.bindiff.gui.tabpanels.projecttabpanel.actions;

import com.google.common.base.Preconditions;
import com.google.security.zynamics.bindiff.gui.tabpanels.projecttabpanel.WorkspaceTabPanelFunctions;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

public class InitialCallGraphSettingsAction extends AbstractAction {
   private final WorkspaceTabPanelFunctions controller;

   public InitialCallGraphSettingsAction(WorkspaceTabPanelFunctions var1) {
      this.controller = (WorkspaceTabPanelFunctions)Preconditions.checkNotNull(var1);
   }

   public void actionPerformed(ActionEvent var1) {
      this.controller.showInitialCallgraphSettingsDialog();
   }
}

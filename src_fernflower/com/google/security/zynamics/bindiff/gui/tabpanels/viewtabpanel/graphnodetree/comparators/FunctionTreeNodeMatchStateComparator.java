package com.google.security.zynamics.bindiff.gui.tabpanels.viewtabpanel.graphnodetree.comparators;

import com.google.common.base.Preconditions;
import com.google.security.zynamics.bindiff.enums.EMatchState;
import com.google.security.zynamics.bindiff.enums.EMatchType;
import com.google.security.zynamics.bindiff.enums.ESortOrder;
import com.google.security.zynamics.bindiff.gui.tabpanels.viewtabpanel.graphnodetree.sorter.ISortableTreeNode;
import java.util.Comparator;

public class FunctionTreeNodeMatchStateComparator implements Comparator {
   private final ESortOrder order;

   public FunctionTreeNodeMatchStateComparator(ESortOrder var1) {
      this.order = (ESortOrder)Preconditions.checkNotNull(var1);
   }

   public int compare(ISortableTreeNode var1, ISortableTreeNode var2) {
      int var3 = 0;
      if(var1.getMatchState() == EMatchState.MATCHED && var2.getMatchState() == EMatchState.MATCHED) {
         if(var1.getMatchType() == var2.getMatchType()) {
            return 0;
         }

         if(var1.getMatchType() != EMatchType.IDENTICAL && var2.getMatchType() == EMatchType.IDENTICAL) {
            var3 = 1;
         } else if(var1.getMatchType() == EMatchType.IDENTICAL && var2.getMatchType() != EMatchType.IDENTICAL) {
            var3 = -1;
         } else if(var1.getMatchType() == EMatchType.INSTRUCTIONS_CHANGED && var2.getMatchType() == EMatchType.STRUCTURAL_CHANGED) {
            var3 = -1;
         } else if(var1.getMatchType() == EMatchType.STRUCTURAL_CHANGED && var2.getMatchType() == EMatchType.INSTRUCTIONS_CHANGED) {
            var3 = 1;
         }
      } else if(var1.getMatchState() != EMatchState.MATCHED && var2.getMatchState() == EMatchState.MATCHED) {
         var3 = 1;
      } else if(var1.getMatchState() == EMatchState.MATCHED && var2.getMatchState() != EMatchState.MATCHED) {
         var3 = -1;
      }

      if(this.order == ESortOrder.DESCENDING) {
         var3 *= -1;
      }

      return var3;
   }
}

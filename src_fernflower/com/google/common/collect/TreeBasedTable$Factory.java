package com.google.common.collect;

import com.google.common.base.Supplier;
import java.io.Serializable;
import java.util.Comparator;
import java.util.TreeMap;

class TreeBasedTable$Factory implements Supplier, Serializable {
   final Comparator comparator;
   private static final long serialVersionUID = 0L;

   TreeBasedTable$Factory(Comparator var1) {
      this.comparator = var1;
   }

   public TreeMap get() {
      return new TreeMap(this.comparator);
   }
}

package com.google.common.collect;

import com.google.common.collect.CollectPreconditions;
import java.util.Iterator;
import java.util.NoSuchElementException;

final class Iterators$2 implements Iterator {
   public boolean hasNext() {
      return false;
   }

   public Object next() {
      throw new NoSuchElementException();
   }

   public void remove() {
      CollectPreconditions.checkRemove(false);
   }
}

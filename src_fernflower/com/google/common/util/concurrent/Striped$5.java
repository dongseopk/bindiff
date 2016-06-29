package com.google.common.util.concurrent;

import com.google.common.base.Supplier;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

final class Striped$5 implements Supplier {
   public ReadWriteLock get() {
      return new ReentrantReadWriteLock();
   }
}

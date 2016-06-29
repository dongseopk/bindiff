package com.google.common.cache;

import com.google.common.base.Supplier;
import com.google.common.cache.AbstractCache$SimpleStatsCounter;
import com.google.common.cache.AbstractCache$StatsCounter;

final class CacheBuilder$2 implements Supplier {
   public AbstractCache$StatsCounter get() {
      return new AbstractCache$SimpleStatsCounter();
   }
}

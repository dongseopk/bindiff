package com.google.common.collect;

import com.google.common.collect.MapMakerInternalMap$ReferenceEntry;
import com.google.common.collect.MapMakerInternalMap$ValueReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;

final class MapMakerInternalMap$SoftValueReference extends SoftReference implements MapMakerInternalMap$ValueReference {
   final MapMakerInternalMap$ReferenceEntry entry;

   MapMakerInternalMap$SoftValueReference(ReferenceQueue var1, Object var2, MapMakerInternalMap$ReferenceEntry var3) {
      super(var2, var1);
      this.entry = var3;
   }

   public MapMakerInternalMap$ReferenceEntry getEntry() {
      return this.entry;
   }

   public void clear(MapMakerInternalMap$ValueReference var1) {
      this.clear();
   }

   public MapMakerInternalMap$ValueReference copyFor(ReferenceQueue var1, Object var2, MapMakerInternalMap$ReferenceEntry var3) {
      return new MapMakerInternalMap$SoftValueReference(var1, var2, var3);
   }

   public boolean isComputingReference() {
      return false;
   }

   public Object waitForValue() {
      return this.get();
   }
}

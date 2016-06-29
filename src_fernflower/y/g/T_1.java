package y.g;

final class T implements y.c.h {
   double[] a;
   int[] b;
   boolean[] c;
   Object[] d;

   T(double[] var1, int[] var2, boolean[] var3, Object[] var4) {
      this.a = var1;
      this.b = var2;
      this.c = var3;
      this.d = var4;
   }

   public Object b(Object var1) {
      try {
         return this.d[((y.c.d)var1).b()];
      } catch (ClassCastException var3) {
         throw new IllegalArgumentException("Argument must be of type Edge.");
      } catch (NullPointerException var4) {
         if(var1 == null) {
            throw new IllegalArgumentException("Argument must be non-null.");
         } else if(var1 instanceof y.c.d && ((y.c.d)var1).a() == null) {
            throw new IllegalArgumentException("Argument is not in graph");
         } else {
            throw new UnsupportedOperationException("Cannot get Object from this type of Map!");
         }
      }
   }

   public double c(Object var1) {
      try {
         return this.a[((y.c.d)var1).b()];
      } catch (ClassCastException var3) {
         throw new IllegalArgumentException("Argument must be of type Edge.");
      } catch (NullPointerException var4) {
         if(var1 == null) {
            throw new IllegalArgumentException("Argument must be non-null.");
         } else if(var1 instanceof y.c.d && ((y.c.d)var1).a() == null) {
            throw new IllegalArgumentException("Argument is not in graph");
         } else {
            throw new UnsupportedOperationException("Cannot get double from this type of Map!");
         }
      }
   }

   public int a(Object var1) {
      try {
         return this.b[((y.c.d)var1).b()];
      } catch (ClassCastException var3) {
         throw new IllegalArgumentException("Argument must be of type Edge.");
      } catch (NullPointerException var4) {
         if(var1 == null) {
            throw new IllegalArgumentException("Argument must be non-null.");
         } else if(var1 instanceof y.c.d && ((y.c.d)var1).a() == null) {
            throw new IllegalArgumentException("Argument is not in graph");
         } else {
            throw new UnsupportedOperationException("Cannot get int from this type of Map!");
         }
      }
   }

   public boolean d(Object var1) {
      try {
         return this.c[((y.c.d)var1).b()];
      } catch (ClassCastException var3) {
         throw new IllegalArgumentException("Argument must be of type Edge.");
      } catch (NullPointerException var4) {
         if(var1 == null) {
            throw new IllegalArgumentException("Argument must be non-null.");
         } else if(var1 instanceof y.c.d && ((y.c.d)var1).a() == null) {
            throw new IllegalArgumentException("Argument is not in graph");
         } else {
            throw new UnsupportedOperationException("Cannot get boolean from this type of Map!");
         }
      }
   }

   public void a(Object var1, Object var2) {
      try {
         this.d[((y.c.d)var1).b()] = var2;
      } catch (ClassCastException var4) {
         throw new IllegalArgumentException("Key must be of type Edge.");
      } catch (NullPointerException var5) {
         if(var1 == null) {
            throw new IllegalArgumentException("Key must be non-null.");
         } else if(var1 instanceof y.c.d && ((y.c.d)var1).a() == null) {
            throw new IllegalArgumentException("Key is not in graph");
         } else {
            throw new UnsupportedOperationException("Cannot set Object in this type of Map!");
         }
      }
   }

   public void a(Object var1, double var2) {
      try {
         this.a[((y.c.d)var1).b()] = var2;
      } catch (ClassCastException var5) {
         throw new IllegalArgumentException("Key must be of type Edge.");
      } catch (NullPointerException var6) {
         if(var1 == null) {
            throw new IllegalArgumentException("Key must be non-null.");
         } else if(var1 instanceof y.c.d && ((y.c.d)var1).a() == null) {
            throw new IllegalArgumentException("Key is not in graph");
         } else {
            throw new UnsupportedOperationException("Cannot set double in this type of Map!");
         }
      }
   }

   public void a(Object var1, int var2) {
      try {
         this.b[((y.c.d)var1).b()] = var2;
      } catch (ClassCastException var4) {
         throw new IllegalArgumentException("Key must be of type Edge.");
      } catch (NullPointerException var5) {
         if(var1 == null) {
            throw new IllegalArgumentException("Key must be non-null.");
         } else if(var1 instanceof y.c.d && ((y.c.d)var1).a() == null) {
            throw new IllegalArgumentException("Key is not in graph");
         } else {
            throw new UnsupportedOperationException("Cannot set int in this type of Map!");
         }
      }
   }

   public void a(Object var1, boolean var2) {
      try {
         this.c[((y.c.d)var1).b()] = var2;
      } catch (ClassCastException var4) {
         throw new IllegalArgumentException("Key must be of type Edge.");
      } catch (NullPointerException var5) {
         if(var1 == null) {
            throw new IllegalArgumentException("Key must be non-null.");
         } else if(var1 instanceof y.c.d && ((y.c.d)var1).a() == null) {
            throw new IllegalArgumentException("Key is not in graph");
         } else {
            throw new UnsupportedOperationException("Cannot set boolean in this type of Map!");
         }
      }
   }
}

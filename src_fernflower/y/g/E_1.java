package y.g;

import y.g.C;
import y.g.F;
import y.g.p;

public class E extends F {
   private y.c.h[] a;
   private y.c.h[] b;

   public E(C var1) {
      super(var1);
   }

   protected void b(y.c.i var1, y.c.i var2) {
      boolean var4 = p.c;
      super.b(var1, var2);
      this.a = var1.w();
      this.b = new y.c.h[this.a.length];
      int var3 = 0;

      while(var3 < this.a.length) {
         this.b[var3] = var2.u();
         ++var3;
         if(var4) {
            break;
         }
      }

   }

   protected void a(y.c.d var1, y.c.d var2) {
      boolean var7 = p.c;
      super.a(var1, var2);
      int var3 = 0;

      while(var3 < this.a.length) {
         y.c.h var4 = this.a[var3];
         y.c.h var5 = this.b[var3];
         Object var6 = this.a(var1, var2, var4.b(var1));
         var5.a(var2, var6);
         ++var3;
         if(var7) {
            break;
         }
      }

   }

   protected Object a(y.c.d var1, y.c.d var2, Object var3) {
      return var3;
   }
}

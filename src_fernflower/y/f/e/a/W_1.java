package y.f.e.a;

import y.f.e.a.Y;

class W implements Y {
   public double a(double var1) {
      return this.b(var1)?0.0D:(1.0D - var1) * (1.0D - var1);
   }

   public boolean b(double var1) {
      return var1 >= 1.0D;
   }
}

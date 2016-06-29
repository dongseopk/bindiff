package org.jfree.data.xy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.jfree.data.DomainOrder;
import org.jfree.data.general.DatasetChangeEvent;
import org.jfree.data.xy.AbstractXYZDataset;
import org.jfree.data.xy.XYZDataset;

public class DefaultXYZDataset extends AbstractXYZDataset implements XYZDataset {
   private List seriesKeys = new ArrayList();
   private List seriesList = new ArrayList();

   public int getSeriesCount() {
      return this.seriesList.size();
   }

   public Comparable getSeriesKey(int var1) {
      if(var1 >= 0 && var1 < this.getSeriesCount()) {
         return (Comparable)this.seriesKeys.get(var1);
      } else {
         throw new IllegalArgumentException("Series index out of bounds");
      }
   }

   public int indexOf(Comparable var1) {
      return this.seriesKeys.indexOf(var1);
   }

   public DomainOrder getDomainOrder() {
      return DomainOrder.NONE;
   }

   public int getItemCount(int var1) {
      if(var1 >= 0 && var1 < this.getSeriesCount()) {
         double[][] var2 = (double[][])((double[][])this.seriesList.get(var1));
         return var2[0].length;
      } else {
         throw new IllegalArgumentException("Series index out of bounds");
      }
   }

   public double getXValue(int var1, int var2) {
      double[][] var3 = (double[][])((double[][])this.seriesList.get(var1));
      return var3[0][var2];
   }

   public Number getX(int var1, int var2) {
      return new Double(this.getXValue(var1, var2));
   }

   public double getYValue(int var1, int var2) {
      double[][] var3 = (double[][])((double[][])this.seriesList.get(var1));
      return var3[1][var2];
   }

   public Number getY(int var1, int var2) {
      return new Double(this.getYValue(var1, var2));
   }

   public double getZValue(int var1, int var2) {
      double[][] var3 = (double[][])((double[][])this.seriesList.get(var1));
      return var3[2][var2];
   }

   public Number getZ(int var1, int var2) {
      return new Double(this.getZValue(var1, var2));
   }

   public void addSeries(Comparable var1, double[][] var2) {
      if(var1 == null) {
         throw new IllegalArgumentException("The \'seriesKey\' cannot be null.");
      } else if(var2 == null) {
         throw new IllegalArgumentException("The \'data\' is null.");
      } else if(var2.length != 3) {
         throw new IllegalArgumentException("The \'data\' array must have length == 3.");
      } else if(var2[0].length == var2[1].length && var2[0].length == var2[2].length) {
         int var3 = this.indexOf(var1);
         if(var3 == -1) {
            this.seriesKeys.add(var1);
            this.seriesList.add(var2);
         } else {
            this.seriesList.remove(var3);
            this.seriesList.add(var3, var2);
         }

         this.notifyListeners(new DatasetChangeEvent(this, this));
      } else {
         throw new IllegalArgumentException("The \'data\' array must contain three arrays all having the same length.");
      }
   }

   public void removeSeries(Comparable var1) {
      int var2 = this.indexOf(var1);
      if(var2 >= 0) {
         this.seriesKeys.remove(var2);
         this.seriesList.remove(var2);
         this.notifyListeners(new DatasetChangeEvent(this, this));
      }

   }

   public boolean equals(Object var1) {
      if(var1 == this) {
         return true;
      } else if(!(var1 instanceof DefaultXYZDataset)) {
         return false;
      } else {
         DefaultXYZDataset var2 = (DefaultXYZDataset)var1;
         if(!this.seriesKeys.equals(var2.seriesKeys)) {
            return false;
         } else {
            for(int var3 = 0; var3 < this.seriesList.size(); ++var3) {
               double[][] var4 = (double[][])((double[][])this.seriesList.get(var3));
               double[][] var5 = (double[][])((double[][])var2.seriesList.get(var3));
               double[] var6 = var4[0];
               double[] var7 = var5[0];
               if(!Arrays.equals(var6, var7)) {
                  return false;
               }

               double[] var8 = var4[1];
               double[] var9 = var5[1];
               if(!Arrays.equals(var8, var9)) {
                  return false;
               }

               double[] var10 = var4[2];
               double[] var11 = var5[2];
               if(!Arrays.equals(var10, var11)) {
                  return false;
               }
            }

            return true;
         }
      }
   }

   public int hashCode() {
      int var1 = this.seriesKeys.hashCode();
      var1 = 29 * var1 + this.seriesList.hashCode();
      return var1;
   }

   public Object clone() {
      DefaultXYZDataset var1 = (DefaultXYZDataset)super.clone();
      var1.seriesKeys = new ArrayList(this.seriesKeys);
      var1.seriesList = new ArrayList(this.seriesList.size());

      for(int var2 = 0; var2 < this.seriesList.size(); ++var2) {
         double[][] var3 = (double[][])((double[][])this.seriesList.get(var2));
         double[] var4 = var3[0];
         double[] var5 = var3[1];
         double[] var6 = var3[2];
         double[] var7 = new double[var4.length];
         double[] var8 = new double[var5.length];
         double[] var9 = new double[var6.length];
         System.arraycopy(var4, 0, var7, 0, var4.length);
         System.arraycopy(var5, 0, var8, 0, var5.length);
         System.arraycopy(var6, 0, var9, 0, var6.length);
         var1.seriesList.add(var2, new double[][]{var7, var8, var9});
      }

      return var1;
   }
}

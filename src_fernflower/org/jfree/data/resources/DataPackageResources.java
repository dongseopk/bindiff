package org.jfree.data.resources;

import java.util.ListResourceBundle;

public class DataPackageResources extends ListResourceBundle {
   private static final Object[][] CONTENTS = new Object[][]{{"series.default-prefix", "Series"}, {"categories.default-prefix", "Category"}};

   public Object[][] getContents() {
      return CONTENTS;
   }
}

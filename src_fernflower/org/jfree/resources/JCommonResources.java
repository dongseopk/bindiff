package org.jfree.resources;

import java.util.ListResourceBundle;

public class JCommonResources extends ListResourceBundle {
   private static final Object[][] CONTENTS = new Object[][]{{"project.name", "JCommon"}, {"project.version", "1.0.9"}, {"project.info", "http://www.jfree.org/jcommon/"}, {"project.copyright", "(C)opyright 2000-2007, by Object Refinery Limited and Contributors"}};

   public Object[][] getContents() {
      return CONTENTS;
   }
}

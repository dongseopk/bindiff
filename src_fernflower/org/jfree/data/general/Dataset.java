package org.jfree.data.general;

import org.jfree.data.general.DatasetChangeListener;
import org.jfree.data.general.DatasetGroup;

public interface Dataset {
   void addChangeListener(DatasetChangeListener var1);

   void removeChangeListener(DatasetChangeListener var1);

   DatasetGroup getGroup();

   void setGroup(DatasetGroup var1);
}

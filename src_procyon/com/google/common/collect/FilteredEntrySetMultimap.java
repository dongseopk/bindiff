package com.google.common.collect;

import com.google.common.annotations.*;
import com.google.common.base.*;
import java.util.*;

@GwtCompatible
final class FilteredEntrySetMultimap extends FilteredEntryMultimap implements FilteredSetMultimap
{
    FilteredEntrySetMultimap(final SetMultimap setMultimap, final Predicate predicate) {
        super(setMultimap, predicate);
    }
    
    @Override
    public SetMultimap unfiltered() {
        return (SetMultimap)this.unfiltered;
    }
    
    @Override
    public Set get(final Object o) {
        return (Set)super.get(o);
    }
    
    @Override
    public Set removeAll(final Object o) {
        return (Set)super.removeAll(o);
    }
    
    @Override
    public Set replaceValues(final Object o, final Iterable iterable) {
        return (Set)super.replaceValues(o, iterable);
    }
    
    @Override
    Set createEntries() {
        return Sets.filter(this.unfiltered().entries(), this.entryPredicate());
    }
    
    @Override
    public Set entries() {
        return (Set)super.entries();
    }
}

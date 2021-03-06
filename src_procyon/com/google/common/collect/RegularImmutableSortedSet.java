package com.google.common.collect;

import com.google.common.annotations.*;
import javax.annotation.*;
import java.util.*;
import com.google.common.base.*;

@GwtCompatible(serializable = true, emulated = true)
final class RegularImmutableSortedSet extends ImmutableSortedSet
{
    private final transient ImmutableList elements;
    
    RegularImmutableSortedSet(final ImmutableList elements, final Comparator comparator) {
        super(comparator);
        this.elements = elements;
    }
    
    @Override
    public UnmodifiableIterator iterator() {
        return this.elements.iterator();
    }
    
    @GwtIncompatible("NavigableSet")
    @Override
    public UnmodifiableIterator descendingIterator() {
        return this.elements.reverse().iterator();
    }
    
    @Override
    public int size() {
        return this.elements.size();
    }
    
    @Override
    public boolean contains(@Nullable final Object o) {
        try {
            return o != null && this.unsafeBinarySearch(o) >= 0;
        }
        catch (ClassCastException ex) {
            return false;
        }
    }
    
    @Override
    public boolean containsAll(Collection elementSet) {
        if (elementSet instanceof Multiset) {
            elementSet = ((Multiset)elementSet).elementSet();
        }
        if (!SortedIterables.hasSameComparator(this.comparator(), elementSet) || elementSet.size() <= 1) {
            return super.containsAll(elementSet);
        }
        final PeekingIterator peekingIterator = Iterators.peekingIterator(this.iterator());
        final Iterator<Object> iterator = elementSet.iterator();
        Object o = iterator.next();
        try {
            while (peekingIterator.hasNext()) {
                final int unsafeCompare = this.unsafeCompare(peekingIterator.peek(), o);
                if (unsafeCompare < 0) {
                    peekingIterator.next();
                }
                else if (unsafeCompare == 0) {
                    if (!iterator.hasNext()) {
                        return true;
                    }
                    o = iterator.next();
                }
                else {
                    if (unsafeCompare > 0) {
                        return false;
                    }
                    continue;
                }
            }
        }
        catch (NullPointerException ex) {
            return false;
        }
        catch (ClassCastException ex2) {
            return false;
        }
        return false;
    }
    
    private int unsafeBinarySearch(final Object o) {
        return Collections.binarySearch(this.elements, o, this.unsafeComparator());
    }
    
    @Override
    boolean isPartialView() {
        return this.elements.isPartialView();
    }
    
    @Override
    int copyIntoArray(final Object[] array, final int n) {
        return this.elements.copyIntoArray(array, n);
    }
    
    @Override
    public boolean equals(@Nullable final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Set)) {
            return false;
        }
        final Set set = (Set)o;
        if (this.size() != set.size()) {
            return false;
        }
        if (this.isEmpty()) {
            return true;
        }
        if (SortedIterables.hasSameComparator(this.comparator, set)) {
            final Iterator<Object> iterator = set.iterator();
            try {
                for (final Object next : this) {
                    final Object next2 = iterator.next();
                    if (next2 == null || this.unsafeCompare(next, next2) != 0) {
                        return false;
                    }
                }
                return true;
            }
            catch (ClassCastException ex) {
                return false;
            }
            catch (NoSuchElementException ex2) {
                return false;
            }
        }
        return this.containsAll(set);
    }
    
    @Override
    public Object first() {
        if (this.isEmpty()) {
            throw new NoSuchElementException();
        }
        return this.elements.get(0);
    }
    
    @Override
    public Object last() {
        if (this.isEmpty()) {
            throw new NoSuchElementException();
        }
        return this.elements.get(this.size() - 1);
    }
    
    @Override
    public Object lower(final Object o) {
        final int n = this.headIndex(o, false) - 1;
        return (n == -1) ? null : this.elements.get(n);
    }
    
    @Override
    public Object floor(final Object o) {
        final int n = this.headIndex(o, true) - 1;
        return (n == -1) ? null : this.elements.get(n);
    }
    
    @Override
    public Object ceiling(final Object o) {
        final int tailIndex = this.tailIndex(o, true);
        return (tailIndex == this.size()) ? null : this.elements.get(tailIndex);
    }
    
    @Override
    public Object higher(final Object o) {
        final int tailIndex = this.tailIndex(o, false);
        return (tailIndex == this.size()) ? null : this.elements.get(tailIndex);
    }
    
    @Override
    ImmutableSortedSet headSetImpl(final Object o, final boolean b) {
        return this.getSubSet(0, this.headIndex(o, b));
    }
    
    int headIndex(final Object o, final boolean b) {
        return SortedLists.binarySearch(this.elements, Preconditions.checkNotNull(o), this.comparator(), b ? SortedLists$KeyPresentBehavior.FIRST_AFTER : SortedLists$KeyPresentBehavior.FIRST_PRESENT, SortedLists$KeyAbsentBehavior.NEXT_HIGHER);
    }
    
    @Override
    ImmutableSortedSet subSetImpl(final Object o, final boolean b, final Object o2, final boolean b2) {
        return this.tailSetImpl(o, b).headSetImpl(o2, b2);
    }
    
    @Override
    ImmutableSortedSet tailSetImpl(final Object o, final boolean b) {
        return this.getSubSet(this.tailIndex(o, b), this.size());
    }
    
    int tailIndex(final Object o, final boolean b) {
        return SortedLists.binarySearch(this.elements, Preconditions.checkNotNull(o), this.comparator(), b ? SortedLists$KeyPresentBehavior.FIRST_PRESENT : SortedLists$KeyPresentBehavior.FIRST_AFTER, SortedLists$KeyAbsentBehavior.NEXT_HIGHER);
    }
    
    Comparator unsafeComparator() {
        return this.comparator;
    }
    
    RegularImmutableSortedSet getSubSet(final int n, final int n2) {
        if (n == 0 && n2 == this.size()) {
            return this;
        }
        if (n < n2) {
            return new RegularImmutableSortedSet(this.elements.subList(n, n2), this.comparator);
        }
        return ImmutableSortedSet.emptySet(this.comparator);
    }
    
    @Override
    int indexOf(@Nullable final Object o) {
        if (o == null) {
            return -1;
        }
        int binarySearch;
        try {
            binarySearch = SortedLists.binarySearch(this.elements, o, this.unsafeComparator(), SortedLists$KeyPresentBehavior.ANY_PRESENT, SortedLists$KeyAbsentBehavior.INVERTED_INSERTION_INDEX);
        }
        catch (ClassCastException ex) {
            return -1;
        }
        return (binarySearch >= 0) ? binarySearch : -1;
    }
    
    @Override
    ImmutableList createAsList() {
        return (this.size() <= 1) ? this.elements : new ImmutableSortedAsList(this, this.elements);
    }
    
    @Override
    ImmutableSortedSet createDescendingSet() {
        final Ordering reverse = Ordering.from(this.comparator).reverse();
        return this.isEmpty() ? ImmutableSortedSet.emptySet(reverse) : new RegularImmutableSortedSet(this.elements.reverse(), reverse);
    }
}

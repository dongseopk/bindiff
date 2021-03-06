package com.google.common.collect;

import com.google.errorprone.annotations.concurrent.*;
import com.google.common.annotations.*;
import com.google.common.base.*;
import java.util.*;
import javax.annotation.*;
import java.io.*;

@GwtCompatible(serializable = true, emulated = true)
public abstract class ImmutableSortedSet extends ImmutableSortedSetFauxverideShim implements SortedIterable, NavigableSet
{
    private static final Comparator NATURAL_ORDER;
    private static final RegularImmutableSortedSet NATURAL_EMPTY_SET;
    final transient Comparator comparator;
    @LazyInit
    @GwtIncompatible("NavigableSet")
    transient ImmutableSortedSet descendingSet;
    
    static RegularImmutableSortedSet emptySet(final Comparator comparator) {
        if (ImmutableSortedSet.NATURAL_ORDER.equals(comparator)) {
            return ImmutableSortedSet.NATURAL_EMPTY_SET;
        }
        return new RegularImmutableSortedSet(ImmutableList.of(), comparator);
    }
    
    public static ImmutableSortedSet of() {
        return ImmutableSortedSet.NATURAL_EMPTY_SET;
    }
    
    public static ImmutableSortedSet of(final Comparable comparable) {
        return new RegularImmutableSortedSet(ImmutableList.of(comparable), Ordering.natural());
    }
    
    public static ImmutableSortedSet of(final Comparable comparable, final Comparable comparable2) {
        return construct(Ordering.natural(), 2, comparable, comparable2);
    }
    
    public static ImmutableSortedSet of(final Comparable comparable, final Comparable comparable2, final Comparable comparable3) {
        return construct(Ordering.natural(), 3, comparable, comparable2, comparable3);
    }
    
    public static ImmutableSortedSet of(final Comparable comparable, final Comparable comparable2, final Comparable comparable3, final Comparable comparable4) {
        return construct(Ordering.natural(), 4, comparable, comparable2, comparable3, comparable4);
    }
    
    public static ImmutableSortedSet of(final Comparable comparable, final Comparable comparable2, final Comparable comparable3, final Comparable comparable4, final Comparable comparable5) {
        return construct(Ordering.natural(), 5, comparable, comparable2, comparable3, comparable4, comparable5);
    }
    
    public static ImmutableSortedSet of(final Comparable comparable, final Comparable comparable2, final Comparable comparable3, final Comparable comparable4, final Comparable comparable5, final Comparable comparable6, final Comparable... array) {
        final Comparable[] array2 = new Comparable[6 + array.length];
        array2[0] = comparable;
        array2[1] = comparable2;
        array2[2] = comparable3;
        array2[3] = comparable4;
        array2[4] = comparable5;
        array2[5] = comparable6;
        System.arraycopy(array, 0, array2, 6, array.length);
        return construct(Ordering.natural(), array2.length, (Object[])array2);
    }
    
    public static ImmutableSortedSet copyOf(final Comparable[] array) {
        return construct(Ordering.natural(), array.length, (Object[])array.clone());
    }
    
    public static ImmutableSortedSet copyOf(final Iterable iterable) {
        return copyOf(Ordering.natural(), iterable);
    }
    
    public static ImmutableSortedSet copyOf(final Collection collection) {
        return copyOf(Ordering.natural(), collection);
    }
    
    public static ImmutableSortedSet copyOf(final Iterator iterator) {
        return copyOf(Ordering.natural(), iterator);
    }
    
    public static ImmutableSortedSet copyOf(final Comparator comparator, final Iterator iterator) {
        return new ImmutableSortedSet$Builder(comparator).addAll(iterator).build();
    }
    
    public static ImmutableSortedSet copyOf(final Comparator comparator, final Iterable iterable) {
        Preconditions.checkNotNull(comparator);
        if (SortedIterables.hasSameComparator(comparator, iterable) && iterable instanceof ImmutableSortedSet) {
            final ImmutableSortedSet set = (ImmutableSortedSet)iterable;
            if (!set.isPartialView()) {
                return set;
            }
        }
        final Object[] array = Iterables.toArray(iterable);
        return construct(comparator, array.length, array);
    }
    
    public static ImmutableSortedSet copyOf(final Comparator comparator, final Collection collection) {
        return copyOf(comparator, (Iterable)collection);
    }
    
    public static ImmutableSortedSet copyOfSorted(final SortedSet set) {
        final Comparator comparator = SortedIterables.comparator(set);
        final ImmutableList copy = ImmutableList.copyOf(set);
        if (copy.isEmpty()) {
            return emptySet(comparator);
        }
        return new RegularImmutableSortedSet(copy, comparator);
    }
    
    static ImmutableSortedSet construct(final Comparator comparator, final int n, final Object... array) {
        if (n == 0) {
            return emptySet(comparator);
        }
        ObjectArrays.checkElementsNotNull(array, n);
        Arrays.sort(array, 0, n, comparator);
        int n2 = 1;
        for (int i = 1; i < n; ++i) {
            final Object o = array[i];
            if (comparator.compare(o, array[n2 - 1]) != 0) {
                array[n2++] = o;
            }
        }
        Arrays.fill(array, n2, n, null);
        return new RegularImmutableSortedSet(ImmutableList.asImmutableList(array, n2), comparator);
    }
    
    public static ImmutableSortedSet$Builder orderedBy(final Comparator comparator) {
        return new ImmutableSortedSet$Builder(comparator);
    }
    
    public static ImmutableSortedSet$Builder reverseOrder() {
        return new ImmutableSortedSet$Builder(Ordering.natural().reverse());
    }
    
    public static ImmutableSortedSet$Builder naturalOrder() {
        return new ImmutableSortedSet$Builder(Ordering.natural());
    }
    
    int unsafeCompare(final Object o, final Object o2) {
        return unsafeCompare(this.comparator, o, o2);
    }
    
    static int unsafeCompare(final Comparator comparator, final Object o, final Object o2) {
        return comparator.compare(o, o2);
    }
    
    ImmutableSortedSet(final Comparator comparator) {
        this.comparator = comparator;
    }
    
    @Override
    public Comparator comparator() {
        return this.comparator;
    }
    
    @Override
    public abstract UnmodifiableIterator iterator();
    
    @Override
    public ImmutableSortedSet headSet(final Object o) {
        return this.headSet(o, false);
    }
    
    @GwtIncompatible("NavigableSet")
    @Override
    public ImmutableSortedSet headSet(final Object o, final boolean b) {
        return this.headSetImpl(Preconditions.checkNotNull(o), b);
    }
    
    @Override
    public ImmutableSortedSet subSet(final Object o, final Object o2) {
        return this.subSet(o, true, o2, false);
    }
    
    @GwtIncompatible("NavigableSet")
    @Override
    public ImmutableSortedSet subSet(final Object o, final boolean b, final Object o2, final boolean b2) {
        Preconditions.checkNotNull(o);
        Preconditions.checkNotNull(o2);
        Preconditions.checkArgument(this.comparator.compare(o, o2) <= 0);
        return this.subSetImpl(o, b, o2, b2);
    }
    
    @Override
    public ImmutableSortedSet tailSet(final Object o) {
        return this.tailSet(o, true);
    }
    
    @GwtIncompatible("NavigableSet")
    @Override
    public ImmutableSortedSet tailSet(final Object o, final boolean b) {
        return this.tailSetImpl(Preconditions.checkNotNull(o), b);
    }
    
    abstract ImmutableSortedSet headSetImpl(final Object p0, final boolean p1);
    
    abstract ImmutableSortedSet subSetImpl(final Object p0, final boolean p1, final Object p2, final boolean p3);
    
    abstract ImmutableSortedSet tailSetImpl(final Object p0, final boolean p1);
    
    @GwtIncompatible("NavigableSet")
    @Override
    public Object lower(final Object o) {
        return Iterators.getNext(this.headSet(o, false).descendingIterator(), null);
    }
    
    @GwtIncompatible("NavigableSet")
    @Override
    public Object floor(final Object o) {
        return Iterators.getNext(this.headSet(o, true).descendingIterator(), null);
    }
    
    @GwtIncompatible("NavigableSet")
    @Override
    public Object ceiling(final Object o) {
        return Iterables.getFirst(this.tailSet(o, true), null);
    }
    
    @GwtIncompatible("NavigableSet")
    @Override
    public Object higher(final Object o) {
        return Iterables.getFirst(this.tailSet(o, false), null);
    }
    
    @Override
    public Object first() {
        return this.iterator().next();
    }
    
    @Override
    public Object last() {
        return this.descendingIterator().next();
    }
    
    @Deprecated
    @GwtIncompatible("NavigableSet")
    @Override
    public final Object pollFirst() {
        throw new UnsupportedOperationException();
    }
    
    @Deprecated
    @GwtIncompatible("NavigableSet")
    @Override
    public final Object pollLast() {
        throw new UnsupportedOperationException();
    }
    
    @GwtIncompatible("NavigableSet")
    @Override
    public ImmutableSortedSet descendingSet() {
        ImmutableSortedSet descendingSet = this.descendingSet;
        if (descendingSet == null) {
            final ImmutableSortedSet descendingSet2 = this.createDescendingSet();
            this.descendingSet = descendingSet2;
            descendingSet = descendingSet2;
            descendingSet.descendingSet = this;
        }
        return descendingSet;
    }
    
    @GwtIncompatible("NavigableSet")
    ImmutableSortedSet createDescendingSet() {
        return new DescendingImmutableSortedSet(this);
    }
    
    @GwtIncompatible("NavigableSet")
    @Override
    public abstract UnmodifiableIterator descendingIterator();
    
    abstract int indexOf(@Nullable final Object p0);
    
    private void readObject(final ObjectInputStream objectInputStream) {
        throw new InvalidObjectException("Use SerializedForm");
    }
    
    @Override
    Object writeReplace() {
        return new ImmutableSortedSet$SerializedForm(this.comparator, this.toArray());
    }
    
    static {
        NATURAL_ORDER = Ordering.natural();
        NATURAL_EMPTY_SET = new RegularImmutableSortedSet(ImmutableList.of(), ImmutableSortedSet.NATURAL_ORDER);
    }
}

package com.epam.preprod.karavayev.nonshop.container;

import com.epam.preprod.karavayev.model.instrument.StringInstrument;
import com.epam.preprod.karavayev.model.myexception.UnmodifiableListException;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class BiListContainer<E extends StringInstrument> implements List<E> {

    private List<E> unmodifiableList;
    private List<E> modifiableList;

    public BiListContainer(List<E> unmodifiableList, List<E> modifiableList) {
        this.unmodifiableList = unmodifiableList;
        this.modifiableList = modifiableList;
    }

    private void checkIfModifiable(int index) {
        if (index < unmodifiableList.size()) {
            throw new UnmodifiableListException("Attempt to mutate unmodifiable data");
        }
    }

    @Override
    public int size() {
        return unmodifiableList.size() + modifiableList.size();
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public boolean contains(Object o) {
        return unmodifiableList.contains(o) || modifiableList.contains(o);
    }

    @Override
    public Iterator<E> iterator() {
        return new BiListIterator();
    }

    private class BiListIterator<E extends StringInstrument> implements Iterator<E> {

        private int current;
        private boolean nextCalled;

        @Override
        public boolean hasNext() {
            return current < size();
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            nextCalled = true;
            return (E) get(current++);
        }

        @Override
        public void remove() {
            if (!nextCalled) {
                throw new IllegalStateException();
            }
            BiListContainer.this.remove(--current);
            nextCalled = false;
        }
    }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[size()];
        System.arraycopy(unmodifiableList.toArray(), 0, array, 0, unmodifiableList.size());
        System.arraycopy(modifiableList.toArray(), 0, array, unmodifiableList.size(), modifiableList.size());
        return array;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        T[] result1 = (T[])unmodifiableList.toArray();
        T[] result2 = (T[])modifiableList.toArray();
        T[] result3 = Arrays.copyOf(result1, a.length < size() ? size() : a.length);
        System.arraycopy(result2, 0, result3, result1.length, result2.length);
        return result3;
    }

    @Override
    public boolean add(E e) {
        return modifiableList.add(e);
    }

    @Override
    public boolean remove(Object o) {
        if (unmodifiableList.contains(o)) {
            throw new UnmodifiableListException("Attempt to mutate unmodifiable data");
        }
        return modifiableList.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object si: c.toArray()) {
            if (!contains(si)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return modifiableList.addAll(c);
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        checkIfModifiable(index);
        return modifiableList.addAll(index - unmodifiableList.size(), c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        for (Object o: c.toArray()) {
            if (unmodifiableList.contains(o)) {
                throw new UnmodifiableListException("Attempt to mutate unmodifiable data");
            }
        }
        return modifiableList.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        for (int i = 0; i < unmodifiableList.size(); i++) {
            if (!c.contains(unmodifiableList.get(i))) {
                throw new UnmodifiableListException("Attempt to mutate unmodifiable data");
            }
        }
        return modifiableList.retainAll(c);
    }

    @Override
    public void clear() {
        if (unmodifiableList.size() > 0) {
            throw new UnmodifiableListException("Attempt to mutate unmodifiable data");
        }
        modifiableList.clear();
    }

    @Override
    public E get(int index) {
        return index < unmodifiableList.size() ?
                unmodifiableList.get(index) :
                modifiableList.get(index - unmodifiableList.size());
    }

    @Override
    public E set(int index, E element) {
        checkIfModifiable(index);
        return modifiableList.set(index - unmodifiableList.size(), element);
    }

    @Override
    public void add(int index, E element) {
        checkIfModifiable(index);
        modifiableList.add(index - unmodifiableList.size(), element);
    }

    @Override
    public E remove(int index) {
        checkIfModifiable(index);
        return modifiableList.remove(index - unmodifiableList.size());
    }

    @Override
    public int indexOf(Object o) {
        int index = unmodifiableList.indexOf(o);
        if (index >= 0) {
            return index;
        }
        index = modifiableList.indexOf(o);
        if (index >= 0) {
            return index + unmodifiableList.size();
        }
        return index;
    }

    @Override
    public int lastIndexOf(Object o) {
        int index = modifiableList.lastIndexOf(o);
        if (index >= 0) {
            return index + modifiableList.size();
        }
        index = unmodifiableList.lastIndexOf(o);
        if (index >= 0) {
            return index;
        }
        return index;
    }

    @Override
    public ListIterator<E> listIterator() {
        throw new UnsupportedOperationException();
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String toString() {
        return "BiListContainer{ " + unmodifiableList.toString() + "; " + modifiableList.toString();
    }
}


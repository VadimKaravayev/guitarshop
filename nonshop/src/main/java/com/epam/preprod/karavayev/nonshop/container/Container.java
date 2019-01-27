package com.epam.preprod.karavayev.nonshop.container;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import com.epam.preprod.karavayev.model.instrument.StringInstrument;

public class Container<E extends StringInstrument> implements List<E> {

    protected StringInstrument[] instruments;
    private int size;

    protected int getSize() {
        return size;
    }

    protected void setSize(int size) {
        this.size = size;
    }

    public Container() {
        instruments = new StringInstrument[10];
    }

    private void increaseSize() {
        instruments = Arrays.copyOf(instruments, instruments.length * 2);
    }

    protected void checkIfIndexCorrect(int index) {
        if (index < 0 || index >= size() ) throw new IndexOutOfBoundsException();
    }


    public Iterator<E> doMagicIterator(Predicate<StringInstrument> predicate) {
        return new MagicIterator(predicate);
    }

    private class MagicIterator<E extends StringInstrument> implements Iterator<E> {

        List<StringInstrument> filtered;
        Iterator<StringInstrument> iter;

        public MagicIterator(Predicate<StringInstrument> predicate) {
            filtered = Container.this.stream().filter(predicate).collect(Collectors.<StringInstrument>toList());
            iter = filtered.iterator();
        }

        @Override
        public boolean hasNext() {
            return iter.hasNext();
        }

        @Override
        public E next() {
            return (E) iter.next();
        }

        @Override
        public void remove() {
            iter.remove();

        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public boolean contains(Object o) {
        for (StringInstrument temp: instruments) {
            if (o.equals(temp)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return new IteratorImpl();
    }

    private class IteratorImpl<E> implements Iterator<E> {
        private int current ;
        private boolean nextCalled;

        @Override
        public boolean hasNext() {
            return current < size();
        }

        @Override
        public E next() {
            nextCalled = true;
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return (E)instruments[current++];
        }

        @Override
        public void remove() {
            if (!nextCalled) {
                throw new IllegalStateException();
            }
            Container.this.remove(--current);
            nextCalled = false;
        }
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(instruments, size());
    }

    @Override
    public <T> T[] toArray(T[] a) {
        if (a.length < size) {
            return (T[])Arrays.copyOf(instruments, size, a.getClass());
        }
        System.arraycopy(instruments, 0, a, 0, size());
        return a;
    }

    @Override
    public boolean add(E e) {
        if (size == instruments.length) {
            increaseSize();
        }
        instruments[size++] = e;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        if (o == null) {
            for (int index = 0; index < size(); index++) {
                if (instruments[index] == null) {
                    remove(index);
                    return true;
                }
            }
        } else {
            for (int index = 0; index < size(); index++) {
                if (instruments[index].equals(o)) {
                    remove(index);
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (int i = 0; i < c.toArray().length; i++) {
            if (!contains(c.toArray()[i])) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        StringInstrument[] tempInstruments = c.toArray(new StringInstrument[0]);
        int oldSize = size();
        instruments = Arrays.copyOf(instruments, size() + tempInstruments.length);
        System.arraycopy(tempInstruments, 0, instruments, oldSize, tempInstruments.length);
        size = instruments.length;
        return oldSize != size;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        checkIfIndexCorrect(index);
        int oldSize = size();
        StringInstrument[] tempInstruments = c.toArray(new StringInstrument[0]);
        StringInstrument[] tempStoringArray = new StringInstrument[size() + tempInstruments.length];
        System.arraycopy(instruments, 0, tempStoringArray, 0, index);
        System.arraycopy(tempInstruments, 0, tempStoringArray, index, tempInstruments.length);
        System.arraycopy(instruments, index, tempStoringArray, tempInstruments.length + index, size() - index);
        instruments = Arrays.copyOf(tempStoringArray, tempStoringArray.length);
        size = instruments.length;
        return oldSize < size();
    }


    @Override
    public boolean removeAll(Collection<?> c) {
        int oldSize = size();
        for (int i = 0, y = 0; i < size() && y < c.size(); i++, y++) {
            if (c.contains(instruments[i])) {
                remove(instruments[i]);
                i--;
            }
        }
        return oldSize > size();
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        int oldSize = size();
        for (int i = 0; i < size(); i++) {
            StringInstrument temp = get(i);
            if (!c.contains(temp)) {
                remove(i);
                i--;
            }
        }
        return oldSize > size();
    }

    @Override
    public void clear() {
        instruments = new StringInstrument[10];
        size = 0;
    }

    @Override
    public E get(int index) {
        checkIfIndexCorrect(index);
        return (E)instruments[index];
    }

    @Override
    public E set(int index, E element) {
        checkIfIndexCorrect(index);
        StringInstrument oldItem = instruments[index];
        instruments[index] = element;
        return (E)oldItem;
    }

    @Override
    public void add(int index, E element) {
        checkIfIndexCorrect(index);
        StringInstrument[] tempInstruments = new StringInstrument[size() + 1];
        for (int i = 0; i < index; i++) {
            tempInstruments[i] = instruments[i];
        }
        tempInstruments[index] = element;
        for (int i = index; i < size(); i++) {
            tempInstruments[i + 1] = instruments[i];
        }
        instruments = tempInstruments;
        size = instruments.length;
    }

    @Override
    public E remove(int index) {
        checkIfIndexCorrect(index);
        E removedElement = (E)instruments[index];
        System.arraycopy(instruments, index + 1, instruments, index, instruments.length - index - 1);
        instruments[--size] = null;
        return removedElement;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < size(); i++) {
            if (instruments[i].equals(o)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        for (int i = size() - 1; i >= 0; i--) {
            if (instruments[i].equals(o)) {
                return i;
            }
        }
        return -1;
    }

    // ********************************** THAT'S ENOUGH **************************************************************
    // - ListIterator и sublist можно не реализовывать

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
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size(); i++) {
            sb.append(get(i) + ": ");
        }
        return sb.toString();
    }
}

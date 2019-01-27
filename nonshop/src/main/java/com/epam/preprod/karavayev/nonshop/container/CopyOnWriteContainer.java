package com.epam.preprod.karavayev.nonshop.container;

import com.epam.preprod.karavayev.model.instrument.StringInstrument;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class CopyOnWriteContainer<E extends StringInstrument> extends Container<E> {

    public CopyOnWriteContainer() {
        instruments = new StringInstrument[0];
    }

    @Override
    public void add(int index, E element) {
        checkIfIndexCorrect(index);
        int len = size();
        StringInstrument[] copyOfInstruments = new StringInstrument[len + 1];
        System.arraycopy(instruments, 0, copyOfInstruments, 0, index);
        System.arraycopy(instruments, index, copyOfInstruments, index + 1, len - index);
        copyOfInstruments[index] = element;
        instruments = copyOfInstruments;
        setSize(instruments.length);
    }

    @Override
    public boolean add(E e) {
        int len = size();
        StringInstrument[] copyOfInstruments = Arrays.copyOf(instruments, len + 1);
        copyOfInstruments[len] = e;
        instruments = copyOfInstruments;
        setSize(instruments.length);
        return true;
    }

    @Override
    public boolean remove(Object o) {
        StringInstrument[] snapshot = Arrays.copyOf(instruments, instruments.length);
        int index = findIndexOfObject(o, snapshot);
        remove(index);
        return true;
    }

    private int findIndexOfObject(Object o, Object[] snapshot) {
        if (o == null) {
            for (int i = 0; i < snapshot.length; i++) {
                if (snapshot[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = 0; i < snapshot.length; i++) {
                if (snapshot[i].equals(o)) {
                    return i;
                }
            }
        }
        return -1;
    }

    @Override
    public E remove(int index) {
        checkIfIndexCorrect(index);
        StringInstrument[] copyOfInstruments = Arrays.copyOf(instruments, instruments.length);
        StringInstrument removed = get(index);
        instruments = removeByIndex(index, copyOfInstruments);
        setSize(instruments.length);
        return (E)removed;
    }

    private StringInstrument[] removeByIndex(int index, Object[] elements) {
        StringInstrument[] result = new StringInstrument[elements.length - 1];
        System.arraycopy(elements, 0, result, 0, index);
        System.arraycopy(elements, index + 1, result, index, elements.length - index - 1);
        return result;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        if (c.size() == 0) {
            return false;
        }
        // make c arg an array
        StringInstrument[] cArray = toArray(new StringInstrument[0]);
        StringInstrument[] copyOfInstruments = Arrays.copyOf(instruments, instruments.length);
        if (size() == 0) {
            instruments = cArray;
        } else {
            StringInstrument[] result = Arrays.copyOf(copyOfInstruments, copyOfInstruments.length + cArray.length);
            System.arraycopy(cArray, 0, result, copyOfInstruments.length, cArray.length);
            instruments = result;
            setSize(instruments.length);
        }
        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        checkIfIndexCorrect(index);
        if (c.size() == 0) {
            return false;
        }
        StringInstrument[] cArray = c.toArray(new StringInstrument[0]);
        StringInstrument[] copyOfInstruments = Arrays.copyOf(instruments, instruments.length);
        if (size() == 0) {
            instruments = cArray;
            setSize(instruments.length);
        } else {
            StringInstrument[] result = new StringInstrument[copyOfInstruments.length + cArray.length];
            System.arraycopy(copyOfInstruments, 0, result, 0, index);
            System.arraycopy(cArray, 0, result, index, cArray.length);
            System.arraycopy(copyOfInstruments, index, result, index + cArray.length, copyOfInstruments.length - index);
            instruments = result;
            setSize(instruments.length);
        }
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        if (c.size() == 0) {
            return false;
        }
        StringInstrument[] copyOfInstruments = Arrays.copyOf(instruments, instruments.length);
        StringInstrument[] updatedArray = new StringInstrument[copyOfInstruments.length];
        // it will define how many elements should be copied after removing all
        int futureLength = 0;

        for (StringInstrument si: copyOfInstruments) {
            if (!c.contains(si)) {
                updatedArray[futureLength++] = si;
            }
        }
        instruments = updatedArray;
        setSize(futureLength);
        return true;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        if (c.size() == 0) {
            return false;
        }
        StringInstrument[] copyOfInstruments = Arrays.copyOf(instruments, instruments.length);;
        StringInstrument[] updatedArray = new StringInstrument[copyOfInstruments.length];
        int futureLength = 0;
        for (int i = 0; i < copyOfInstruments.length; i++) {
            StringInstrument instrument = copyOfInstruments[i];
            if (c.contains(instrument)) {
                updatedArray[futureLength++] = instrument;
            }
        }
        instruments = updatedArray;
        setSize(futureLength);
        return true;
    }

    @Override
    public E set(int index, E element) {
        checkIfIndexCorrect(index);
        StringInstrument[] copyOfInstruments = Arrays.copyOf(instruments, instruments.length);;
        StringInstrument oldValue = copyOfInstruments[index];
        StringInstrument[] result = Arrays.copyOf(copyOfInstruments, copyOfInstruments.length);
        result[index] = element;
        instruments = result;
        setSize(instruments.length);
        return (E) oldValue;
    }

    @Override
    public Iterator<E> iterator() {
        return new CopyOnWriteIterator<>();
    }

    private class CopyOnWriteIterator<E> implements Iterator<E> {

        private StringInstrument[] snapshot;
        private int current;

        private CopyOnWriteIterator() {
            snapshot = instruments;
        }

        @Override
        public boolean hasNext() {
            return current < snapshot.length;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return (E) snapshot[current++];
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}

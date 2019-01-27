package com.epam.preprod.karavayev.nonshop.container;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

public class NonDuplicateList<E> extends ArrayList<E> {

    public NonDuplicateList() {
        super();
    }

    public NonDuplicateList(Collection<? extends E> c) {
        super(c.stream().distinct().collect(Collectors.toList()));
    }

    @Override
    public E set(int index, E element) {
        if (contains(element) && !get(index).equals(element)) {
            return null;
        }
        return super.set(index, element);
    }

    @Override
    public boolean add(E e) {
        if (contains(e)) {
            return false;
        }
        return super.add(e);
    }

    @Override
    public void add(int index, E element) {
        if (contains(element)) {
            return;
        }
        super.add(index, element);
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        for (E element: c) {
            if (!contains(element)) {
                add(element);
            }
        }
        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        for (E element: c) {
            if (!contains(element)) {
                add(element);
            }
        }
        return true;
    }
}

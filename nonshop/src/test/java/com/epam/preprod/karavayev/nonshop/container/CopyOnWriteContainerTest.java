package com.epam.preprod.karavayev.nonshop.container;

import com.epam.preprod.karavayev.model.instrument.Guitar;
import com.epam.preprod.karavayev.model.instrument.StringInstrument;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Iterator;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsCollectionContaining.hasItems;
import static org.junit.Assert.*;

;

public class CopyOnWriteContainerTest {

    private CopyOnWriteContainer<StringInstrument> container;
    private Iterator<StringInstrument> iter;

    @Before
    public void setUp() {
        container = new CopyOnWriteContainer<>();
        container.add(new Guitar("Taylor", new BigDecimal(120)));
        container.add(new Guitar("Mitchell", new BigDecimal(120)));
        container.add(new Guitar("Chapman", new BigDecimal(120)));
        iter = container.iterator();
    }

    @Test(expected = UnsupportedOperationException.class)
    public void shouldThrowExceptionOnRemoveAtIteration() {
        iter = container.iterator();
        iter.next();
        iter.remove();
    }

    @Test
    public void shouldReturnElemetsOfContainerAtIteration() {
        StringInstrument element2;
        iter.next();
        element2 = iter.next();
        assertThat(container.get(1), is(element2));
    }

    @Test
    public void shouldReturnTrueIfIterationHasMoreElements() {
        for (StringInstrument si: container) {
            assertTrue(container.contains(si));
        }
    }

    @Test
    public void shouldAddElementToContainer() {
        StringInstrument instrument = new Guitar("Fender", new BigDecimal(110));
        container.add(instrument);
        assertEquals(4, container.size());
        assertEquals(instrument, container.get(3));
    }

    @Test
    public void shouldInsertElementToContainerByIndex() {
        StringInstrument instrument = new Guitar("Fender", new BigDecimal(110));
        container.add(1, instrument);
        assertEquals(4, container.size());
        assertEquals(instrument, container.get(1));
    }

    @Test
    public void shouldRemoveElementByIndex() {
        StringInstrument toRemove = container.get(1);
        StringInstrument shifted = container.get(2);
        StringInstrument removed = container.remove(1);
        assertEquals(toRemove, removed);
        assertEquals(2, container.size());
        assertEquals(shifted, container.get(1));
    }

    @Test
    public void shouldRemoveElementAtFirstOccurrence() {
        container = new CopyOnWriteContainer<>();
        container.add(new Guitar("Fender", new BigDecimal(120)));
        container.add(new Guitar("Taylor", new BigDecimal(120)));
        container.add(new Guitar("Fender", new BigDecimal(120)));
        container.add(new Guitar("Taylor", new BigDecimal(120)));
        StringInstrument toRemove = container.get(1);
        container.remove(toRemove);
        assertEquals(3, container.size());
        assertFalse(toRemove.equals(container.get(1)));
    }

    @Test
    public void shouldAppendSpecifiedCollectionToEndOfContainer() {
        Container<StringInstrument> containerToAdd = new CopyOnWriteContainer<>();
        containerToAdd.add(new Guitar("Taylor", new BigDecimal(120)));
        containerToAdd.add(new Guitar("Mitchell", new BigDecimal(120)));
        containerToAdd.add(new Guitar("Chapman", new BigDecimal(120)));
        assertTrue(container.addAll(containerToAdd));
        assertEquals(6, container.size());
    }

    @Test
    public void shouldInsertSpecifiedCollectionIntoContainerAtSpecifiedPosition() {
        Container<StringInstrument> containerToAdd = new CopyOnWriteContainer<>();
        StringInstrument s1 = new Guitar("Yamaha", new BigDecimal(120));
        containerToAdd.add(s1);
        containerToAdd.add(new Guitar("Mitchell", new BigDecimal(120)));
        containerToAdd.add(new Guitar("Chapman", new BigDecimal(120)));
        assertTrue(container.addAll(1, containerToAdd));
        assertEquals(6, container.size());
        assertEquals(s1, container.get(1));
    }

    @Test
    public void shouldRemoveTheElementsFromContainerThatAreContainedInSpecifiedCollection() {
        Container<StringInstrument> argContainer = new CopyOnWriteContainer<>();
        StringInstrument remainingElement =container.get(2);
        argContainer.add(new Guitar("Taylor", new BigDecimal(120)));
        argContainer.add(new Guitar("Mitchell", new BigDecimal(120)));
        assertTrue(container.removeAll(argContainer));
        assertEquals(1, container.size());
        assertEquals(remainingElement, container.get(0));
    }

    @Test
    public void shouldRetainTheElementsInContainerThatAreContainedInTheSpecifiedCollection() {
        Container<StringInstrument> argContainer = new CopyOnWriteContainer<>();
        argContainer.add(new Guitar("Taylor", new BigDecimal(120)));
        argContainer.add(new Guitar("Mitchell", new BigDecimal(120)));
        assertTrue(container.retainAll(argContainer));
        assertThat(container, hasItems(argContainer.get(0), argContainer.get(1)));
        assertThat(container.size(), is(2));
    }

    @Test
    public void shouldRemoveAllElementsFromContainer() {
        container.clear();
        assertThat(container.size(), is(0));
    }

    @Test
    public void shouldReplaceElementAtSpecifiedPositionInContainerWithSpecifiedElement () {
        StringInstrument element = new Guitar("Yamaha", new BigDecimal(120));
        container.set(1, element);
        assertThat(container, hasItems(element));
    }
}
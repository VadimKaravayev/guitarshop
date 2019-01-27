package com.epam.preprod.karavayev.nonshop.container;

import com.epam.preprod.karavayev.model.instrument.Guitar;
import com.epam.preprod.karavayev.model.instrument.StringInstrument;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class ContainerTest {

    private Container<StringInstrument> container;
    private Iterator<StringInstrument> iter;

    @Before
    public void setUp() {
        container = new Container<>();
        container.add(new Guitar(1, "Taylor", new BigDecimal(120)));
        container.add(new Guitar(2, "Mitchell", new BigDecimal(120)));
        container.add(new Guitar(3, "Chapman", new BigDecimal(120)));
        iter = container.iterator();
    }

    @Test
    public void shouldAddElementToContainerByIndex() {
        StringInstrument s1 = new Guitar(1, "Yamaha", new BigDecimal(120));
        StringInstrument shiftedElement = container.get(1);
        container.add(1, s1);
        assertEquals(s1, container.get(1));
        assertEquals(4, container.size());
        assertEquals(shiftedElement, container.get(2));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void shouldThrowExceptionIfIndexOutOfRange() {
        StringInstrument s1 = new Guitar(1, "Yamaha", new BigDecimal(120));
        container.add(container.size(), s1);
    }

    @Test
    public void shouldAppendElementToEndOfList() {
        StringInstrument s1 = new Guitar(1, "Yamaha", new BigDecimal(120));
        container.add(s1);
        assertEquals(4, container.size());
        assertEquals(s1, container.get(3));
    }

    @Test
    public void shouldReturnElementAtSpecifiedPosition() {
        StringInstrument expectedElement = new Guitar(3, "Chapman", new BigDecimal(120));
        assertEquals(expectedElement, container.get(2));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void shouldThrowExceptionIfGetMethodReceivesArgGreaterThanIndexBound() {
        container.get(container.size());
    }

    @Test
    public void shouldRemoveElementAtSpecifiedPositionAndShiftSubsequentElementsToLeft() {
        int index = 1;
        int startSize = container.size();
        int expectedSize = startSize - 1;
        StringInstrument expectedReturnedElement = container.get(1);
        StringInstrument expectedShiftedElement = container.get(2);
        StringInstrument actualReturnedElement = container.remove(1);
        assertEquals(2, container.size());
        assertEquals(expectedReturnedElement, actualReturnedElement);
        assertEquals(expectedShiftedElement, container.get(1));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void shouldThrowExceptionIfRemoveReceivesArgumentGreaterThanIndexBound() {
        container.remove(container.size() + 1);
    }

    @Test
    public void shouldRemoveElementAtItsFirstOccurrence() {
        StringInstrument expectedShiftedElement = container.get(1);
        assertTrue(container.remove(container.get(0)));
        assertEquals(2, container.size());
        assertEquals(expectedShiftedElement, container.get(0));
    }

    @Test
    public void shouldCheckIfContainerHasAnotherElement() {
        container = new Container<>();
        iter = container.iterator();
        Assert.assertFalse(iter.hasNext());
        container.add(new Guitar(1, "Taylor", new BigDecimal(120)));
        container.add(new Guitar(2, "Mitchell", new BigDecimal(120)));
        container.add(new Guitar(3, "Chapman", new BigDecimal(120)));
        Assert.assertTrue(iter.hasNext());
    }

    @Test
    public void shouldReturnNextElementInIteration() {
        StringInstrument expectedElement = container.get(0);
        assertEquals(expectedElement, iter.next());
    }

    @Test(expected = NoSuchElementException.class)
    public void shouldThrowExceptionIfNoNextElement() {
        container = new Container<>();
        iter = container.iterator();
        iter.next();
    }

    @Test(expected = IllegalStateException.class)
    public void shouldThrowExceptionIfRemoveCalledTwiceInARow() {
        iter.next();
        iter.remove();
        iter.remove();
    }

    @Test
    public void shouldRemoveElementFromContainerInIteration() {
        int startSize = container.size();
        iter.next();
        iter.remove();
        assertEquals(2, container.size());
    }

    @Test
    public void shouldRetainOnlyElementsInListThatAreContainedInSpecifiedCollection() {
        Container<StringInstrument> testContainer = new Container<>();
        StringInstrument param = new Guitar(1, "Taylor", new BigDecimal(120));
        testContainer.add(param);
        container.retainAll(testContainer);
        assertThat(container.size(), is(1));
        assertTrue(container.contains(param));
    }

    @Test
    public void shouldAddLotsOfElement() {
        for (int i = 0; i < 50; i++) {
            container.add(new Guitar());
        }
    }

    @Test
    public void shouldReturnIteratorMeetingRequirementsInPredicate() {
        container.add(new Guitar(4, "Ibanez", new BigDecimal(90)));
        container.add(new Guitar(5, "Epiphone", new BigDecimal(95)));
        container.add(new Guitar(6, "Jackson", new BigDecimal(80)));
        Iterator<StringInstrument> iterator = container.doMagicIterator(e-> e.getPrice().intValue() > 100);
        while (iterator.hasNext()) {
            assertTrue(iterator.next().getPrice().intValue() > 100);
        }

    }

    @Test
    public void shouldReturnArrayContainingAllOfElementsInContainerInProperSequence() {
        StringInstrument[] result = container.toArray(new StringInstrument[9]);
        assertThat(result.length, is(9));
        assertTrue(result.getClass().isArray());
        for (int i = 0; i < container.size(); i++) {
            assertTrue(result[i].equals(container.get(i)));
        }

    }


}

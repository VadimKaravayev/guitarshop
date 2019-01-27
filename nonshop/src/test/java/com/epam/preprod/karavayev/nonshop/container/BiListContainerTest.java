package com.epam.preprod.karavayev.nonshop.container;

import com.epam.preprod.karavayev.model.instrument.Guitar;
import com.epam.preprod.karavayev.model.instrument.StringInstrument;
import com.epam.preprod.karavayev.model.myexception.UnmodifiableListException;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.IntStream;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.*;

public class BiListContainerTest {
    private List<StringInstrument> biListContainer;
    private List<StringInstrument> unmodifiableList;
    private List<StringInstrument> modifiableList;
    private StringInstrument guitar1;
    private StringInstrument guitar2;

    @Before
    public void setUp() {
        guitar1 = new Guitar("Taylor", new BigDecimal(120));
        guitar2 = new Guitar("Yamaha", new BigDecimal(120));
        unmodifiableList = new Container<>();
        unmodifiableList.add(guitar1);
        unmodifiableList.add(new Guitar("Mitchell", new BigDecimal(120)));
        unmodifiableList.add(new Guitar("Chapman", new BigDecimal(120)));
        modifiableList = new Container<>();
        modifiableList.add(guitar2);
        modifiableList.add(new Guitar("Fender", new BigDecimal(120)));
        modifiableList.add(new Guitar("Simens", new BigDecimal(120)));
        modifiableList.add(new Guitar("Lenovo", new BigDecimal(120)));
        biListContainer = new BiListContainer<>(unmodifiableList, modifiableList);

    }

    @Test
    public void shouldReturnSizeOfBiList() {
        assertThat(biListContainer.size(), is(7));
    }

    @Test
    public void shouldReturnTrueIfbiListContainsNoElements() {
        List<StringInstrument> list1 = new Container<>();
        List<StringInstrument> list2 = new Container<>();
        biListContainer = new BiListContainer<>(list1, list2);
        assertTrue(biListContainer.isEmpty());
    }

    @Test
    public void shouldReturnTrueIfBiListContainsSpecifiedElement() {
        StringInstrument s1 = new Guitar("Taylor", new BigDecimal(120));
        StringInstrument s2 = new Guitar("Simens", new BigDecimal(120));
        StringInstrument s3 = new Guitar("Noname", new BigDecimal(120));
        assertTrue(biListContainer.contains(s1));
        assertTrue(biListContainer.contains(s2));
        assertFalse(biListContainer.contains(s3));
    }

    @Test
    public void shouldReturnArrayContainingAllElementsOfBiListInProperSequence() {
        Object[] result = biListContainer.toArray();
        assertTrue(result.getClass().isArray());
        IntStream.range(0, result.length)
                .forEach(i-> assertThat(biListContainer.get(i), is(result[i])));
    }

    @Test
    public void shouldAppendElementToEndOfBiList() {
        StringInstrument s1 = new Guitar("Taylor", new BigDecimal(120));
        assertTrue(biListContainer.add(s1));
        assertThat(biListContainer.size(), is(8));
        assertTrue(biListContainer.contains(s1));
    }

    @Test
    public void shouldRemoveFirstOccurrenceOfSpecifiedElementFromBiList() {
        StringInstrument s1 = new Guitar("Yamaha", new BigDecimal(120));
        StringInstrument noSuchElement = new Guitar("No shouldSaveContainerToFile", new BigDecimal(120));
        assertTrue(biListContainer.remove(s1));
        assertThat(biListContainer.size(), is(6));
        assertFalse(biListContainer.remove(noSuchElement));
    }

    @Test(expected = UnmodifiableListException.class)
    public void shouldThrowExceptionOnAttemptToDeleteUnmodifiableElement() {
        StringInstrument fromUnmodPart = new Guitar("Taylor", new BigDecimal(120));
        biListContainer.remove(fromUnmodPart);
    }

    @Test
    public void shouldReturnTrueIfBiListContainsAllElementsOfSpecifiedCollection() {
        Collection<StringInstrument> collection = new ArrayList<>();
        collection.addAll(Arrays.asList(guitar1, guitar2));
        assertTrue(biListContainer.containsAll(collection));
        collection.add(new Guitar("No shouldSaveContainerToFile", new BigDecimal(120)));
        assertFalse(biListContainer.containsAll(collection));
    }

    @Test
    public void shouldReturnElementAtSpecifiedPositionInBiList() {
        StringInstrument s1 = new Guitar("Fender", new BigDecimal(120));
        assertEquals(s1, biListContainer.get(4));
    }

    @Test
    public void shouldAppendElementsInSpecifiedCollectionToEndOfBiList() {
        List<StringInstrument> testList = new Container<>();
        testList.add(guitar1);
        testList.add(guitar2);
        assertTrue(biListContainer.addAll(testList));
        assertThat(biListContainer.size(), is(9));

    }

    @Test
    public void shouldInsertAllElementsInSpecifiedCollectionIntoBiListAtSpecifiedPosition() {
        List<StringInstrument> testList = new Container<>();
        testList.add(guitar1);
        testList.add(guitar2);
        assertTrue(biListContainer.addAll(4, testList));
        assertThat(biListContainer.size(), is(9));
        assertEquals(guitar1, biListContainer.get(4));
    }

    @Test(expected = UnmodifiableListException.class)
    public void shouldThrowExceptionOnCollectionInsertionAtIndexOfUnmodifiedPartOfBiList() {
        List<StringInstrument> testList = new Container<>();
        testList.add(guitar1);
        testList.add(guitar2);
        biListContainer.addAll(1, testList);
    }

    @Test
    public void shouldRemoveAllElementsFromBiListThatAreContainedInSpecifiedCollection() {
        List<StringInstrument> testList = new Container<>();
        unmodifiableList = new Container<>();
        modifiableList = new Container<>();
        modifiableList.add(guitar1);
        modifiableList.add(guitar2);
        biListContainer = new BiListContainer<>(unmodifiableList, modifiableList);
        testList.add(guitar1);
        testList.add(guitar2);
        assertTrue(biListContainer.removeAll(testList));
        assertThat(biListContainer.size(), is(0));
    }

    @Test(expected = UnmodifiableListException.class)
    public void shouldThrowExceptionOnAttemptToRemoveUnmodifiableData() {
        List<StringInstrument> testList = new Container<>();
        testList.add(guitar1);
        testList.add(guitar2);
        biListContainer.removeAll(testList);
    }

    @Test
    public void shouldRetainOnlyElementsInBiListThatAreContainedInSpecifiedCollection() {
        List<StringInstrument> testList = new Container<>();
        testList.add(guitar1);
        testList.add(new Guitar("Mitchell", new BigDecimal(120)));
        testList.add(new Guitar("Chapman", new BigDecimal(120)));
        assertTrue(biListContainer.retainAll(testList));
        assertThat(biListContainer.size(), is(3));
    }

    @Test
    public void shouldRemoveElementFromThisLis() {
        unmodifiableList = new Container<>();
        biListContainer = new BiListContainer<>(unmodifiableList, modifiableList);
        biListContainer.clear();
        assertThat(biListContainer.size(), is(0));
    }

    @Test(expected = UnmodifiableListException.class)
    public void shouldThrowExceptionOnCallingClearMethodWhenUnmodifiableListHasElements() {
        biListContainer.clear();
    }

    @Test
    public void shouldReplaceElementAtSpecifiedPositionInThisBiListModifiablePartWithSpecifiedElement() {
        StringInstrument newElement = new Guitar("Mitchell", new BigDecimal(120));
        StringInstrument replacedElement = biListContainer.set(4, newElement);
        assertFalse(biListContainer.contains(replacedElement));
        assertEquals(newElement, biListContainer.get(4));
    }

    @Test(expected = UnmodifiableListException.class)
    public void shouldThrowExceptionOnAttempToReplaceUnmodifiableElementInBiList() {
        biListContainer.set(1, guitar1);
    }

    @Test
    public void shouldInsertSpecifiedElementAtSpecifiedPositionInModifiedPartOfBiList() {
        biListContainer.add(4, guitar1);
        assertThat(biListContainer.size(), is(8));
        assertEquals(guitar1, biListContainer.get(4));
    }

    @Test(expected = UnmodifiableListException.class)
    public void shouldThrowExceptionOnAttemptToAddElementByIndexOfUnmodifiableData() {
        biListContainer.add(1, guitar1);
    }

    @Test
    public void ShouldRemoveElementAtSpecifiedPositionInBiListModifiablePart() {
        StringInstrument toRemove = biListContainer.get(4);
        StringInstrument theRemoved = biListContainer.remove(4);
        assertThat(biListContainer.size(), is(6));
        assertEquals(toRemove, theRemoved);
    }

    @Test(expected = UnmodifiableListException.class)
    public void shouldThrowExceptionOnRemovingUnmodifiableElementByIndex() {
        biListContainer.remove(2);
    }

    @Test
    public void shouldReturnIndexOfFirstOccurrenceOfSpecifiedElementInBiList() {
        assertThat(biListContainer.indexOf(guitar1), is(0));
        assertThat(biListContainer.indexOf(guitar2), is(3));
    }

    @Test
    public void shouldReturnMinusOneIfBiListHasNoSpecifiedElement() {
        StringInstrument noSuchElement = new Guitar("Rio", new BigDecimal(10));
        assertThat(biListContainer.indexOf(noSuchElement), is(-1));
    }

    @Test
    public void shouldReturnIndexOfLastOccurrenceOfSpecifiedElementInBiList() {
        unmodifiableList = new Container<>();
        unmodifiableList.add(guitar1);
        unmodifiableList.add(guitar1);
        unmodifiableList.add(guitar1);
        modifiableList = new Container<>();
        modifiableList.add(guitar2);
        modifiableList.add(guitar2);
        modifiableList.add(guitar2);
        biListContainer = new BiListContainer<>(unmodifiableList, modifiableList);
        assertThat(biListContainer.lastIndexOf(guitar1), is(2));
        assertThat(biListContainer.lastIndexOf(guitar2), is(5));
    }

    @Test
    public void shouldReturnMinusOneIfBiListHasNoLastIndexOfSpecifiedElement() {
        StringInstrument noSuchElement = new Guitar("Rio", new BigDecimal(10));
        assertThat(biListContainer.lastIndexOf(noSuchElement), is(-1));
    }

    @Test
    public void shouldReturnTrueIfIterationHasMoreElements() {
        for (StringInstrument si: biListContainer) {
            assertTrue(biListContainer.contains(si));
        }
    }

    @Test
    public void shouldReturnFalseIfIterationHasNoElements() {
        Iterator<StringInstrument> iterator = biListContainer.iterator();
        iterator.next();
        iterator.next();
        iterator.next();
        iterator.next();
        iterator.next();
        iterator.next();
        iterator.next();
        assertFalse(iterator.hasNext());
    }

    @Test
    public void shouldReturnTheNextElementInTheIteration() {
        Iterator<StringInstrument> iterator = biListContainer.iterator();
        assertEquals(iterator.next(), biListContainer.get(0));
        assertEquals(iterator.next(), biListContainer.get(1));
        assertEquals(iterator.next(), biListContainer.get(2));
        assertEquals(iterator.next(), biListContainer.get(3));
    }

    @Test(expected = NoSuchElementException.class)
    public void shouldThrowExceptionIfTheIterationHasNoMoreElements() {
        Iterator<StringInstrument> iterator = biListContainer.iterator();
        for (int i = 0; i < biListContainer.size() + 1; i++) {
            iterator.next();
        }
    }

    @Test
    public void shouldRemoveFromUnderlyingCollectionLastElementReturnedByIterator() {
        Iterator<StringInstrument> iterator = biListContainer.iterator();
        iterator.next();
        iterator.next();
        iterator.next();
        StringInstrument lastReturn = iterator.next();
        assertThat(biListContainer.indexOf(lastReturn), is(3));
        iterator.remove();
        assertFalse(biListContainer.contains(lastReturn));
    }

    @Test(expected = UnmodifiableListException.class)
    public void shouldThrowExceptionOnAttemptToRemoveUnmodifiableElementWhileIteration() {
        Iterator<StringInstrument> iterator = biListContainer.iterator();
        StringInstrument lastReturn = iterator.next();
        iterator.remove();
        assertFalse(biListContainer.contains(lastReturn));
    }

    @Test(expected = IllegalStateException.class)
    public void shouldThrowExceptionWhenRemoveCalledTwiceInARow() {
        Iterator<StringInstrument> iterator = biListContainer.iterator();
        iterator.remove();
        iterator.remove();
    }

    @Test
    public void shouldReturnArrayOfBiListElementsInProperSequencePaddedWithNullsIfArrayLengthGreaterThanSize() {
        StringInstrument[] result = biListContainer.toArray(new StringInstrument[10]);
        assertTrue(result.getClass().isArray());
        assertThat(result.length, is(10));
        IntStream.range(0, biListContainer.size())
                .forEach(i-> assertEquals(biListContainer.get(i), result[i]));
        IntStream.rangeClosed(7, 9)
                .forEach(i-> assertThat(result[i], is(nullValue())));
    }
}
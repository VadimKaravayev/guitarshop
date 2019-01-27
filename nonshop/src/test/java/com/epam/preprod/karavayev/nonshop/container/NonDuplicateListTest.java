package com.epam.preprod.karavayev.nonshop.container;

import com.epam.preprod.karavayev.model.instrument.Guitar;
import com.epam.preprod.karavayev.model.instrument.StringInstrument;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;

public class NonDuplicateListTest {

    private List<StringInstrument> container1;

    @Before
    public void setUp() {
        container1 = new NonDuplicateList<>();
        StringInstrument guitar1 = new Guitar("Ibanez", new BigDecimal(500));
        StringInstrument guitar2 = new Guitar("Magma", new BigDecimal(500));
        StringInstrument guitar3 = new Guitar("Yamaha", new BigDecimal(500));
        container1.add(guitar1);
        container1.add(guitar2);
        container1.add(guitar3);
    }

    @Test
    public void shouldReceiveCollectionToConstructorAndRemoveDuplicates() {
        StringInstrument guitar1 = new Guitar("Ibanez", new BigDecimal(500));
        StringInstrument guitar2 = new Guitar("Ibanez", new BigDecimal(500));
        StringInstrument guitar3 = new Guitar("Magma", new BigDecimal(500));
        List<StringInstrument> collection = new ArrayList<>();
        collection.add(guitar1);
        collection.add(guitar2);
        collection.add(guitar3);
        container1 = new NonDuplicateList<>(collection);
        assertThat(container1.size(), is(2));
    }

    @Test
    public void shouldNotSetElementIfItExistsInList() {
        StringInstrument guitar4 = new Guitar("Yamaha", new BigDecimal(500));
        assertThat(container1.set(1, guitar4), is(nullValue()));
    }

    @Test
    public void shouldAllowSetExistingElementWithTheSameElement() {
        StringInstrument guitar4 = new Guitar("Yamaha", new BigDecimal(500));
        container1.set(2, guitar4);
        assertEquals(guitar4, container1.get(2));
    }

    @Test
    public void shouldNotAllowAddingOfExistingElementToEndOfList() {
        StringInstrument guitar4 = new Guitar("Yamaha", new BigDecimal(500));
        assertFalse(container1.add(guitar4));
    }

    @Test
    public void shouldNotAllowAddingToEndOfListACollectionWhichContainsAtLeastOneExistingElement() {
        StringInstrument guitar1 = new Guitar("Ibanez", new BigDecimal(500));
        StringInstrument guitar2 = new Guitar("Fender", new BigDecimal(500));
        StringInstrument guitar3 = new Guitar("Magma", new BigDecimal(500));
        List<StringInstrument> collection = new ArrayList<>();
        collection.add(guitar1);
        collection.add(guitar2);
        collection.add(guitar3);
        container1.addAll(collection);
        assertThat(container1.size(), is(4));
    }

    @Test
    public void shouldNotAllowAddingByIndexACollectionWhichContainsExistingElements() {
        StringInstrument guitar1 = new Guitar("Ibanez", new BigDecimal(500));
        StringInstrument guitar2 = new Guitar("Fender", new BigDecimal(500));
        StringInstrument guitar3 = new Guitar("Magma", new BigDecimal(500));
        List<StringInstrument> collection = new ArrayList<>();
        collection.add(guitar1);
        collection.add(guitar2);
        collection.add(guitar3);
        container1.addAll(2, collection);
        assertThat(container1.size(), is(4));
    }

    @Test
    public void shouldAddManyElements() {
        StringInstrument guitar2 = new Guitar("Magma", new BigDecimal(500));
        StringInstrument guitar3 = new Guitar("Gibson", new BigDecimal(500));
        List<StringInstrument> list = new ArrayList<>();
        list.add(guitar2);
        list.add(guitar3);
        container1.addAll(list);
        assertThat(container1.size(), is(4));
    }
}
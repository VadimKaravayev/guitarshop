package com.epam.preprod.karavayev.nonshop.task7part2proxy;

import com.epam.preprod.karavayev.model.instrument.GuitarType;
import com.epam.preprod.karavayev.model.instrument.Product;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class BasedOnMapInstrumentTest {

    private Product product;

    @Before
    public void setUp() {
        product = new BasedOnMapInstrument().getNewInstrument();
    }

    @Test
    public void shouldImplementProductInterfaceUsingMapMethodsGetAndPut() {

        product.setId(1);
        assertThat(product.getId(), is(1));

        product.setName("Yamaha");
        assertThat(product.getName(), is("Yamaha"));

        product.setPrice(new BigDecimal(1200));
        assertThat(product.getPrice(), is(new BigDecimal(1200)));

        product.setGuitarType(GuitarType.ACOUSTIC);
        assertThat(product.getGuitarType(), is(GuitarType.ACOUSTIC));
    }
}
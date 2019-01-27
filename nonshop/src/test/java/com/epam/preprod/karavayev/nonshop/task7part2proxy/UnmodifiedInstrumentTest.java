package com.epam.preprod.karavayev.nonshop.task7part2proxy;

import com.epam.preprod.karavayev.model.instrument.Guitar;
import com.epam.preprod.karavayev.model.instrument.GuitarType;
import com.epam.preprod.karavayev.model.instrument.Product;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

public class UnmodifiedInstrumentTest {

    private Product guitar;

    @Before
    public void setUp() {
        guitar = new Guitar();
        guitar.setId(1);
        guitar.setName("Yamaha");
        guitar.setGuitarType(GuitarType.ELECTRIC);
        guitar.setPrice(BigDecimal.TEN);
        guitar = new UnmodifiedInstrument().create(guitar);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void shouldThrowUnsupportedOperationExceptionOnAttemptToAnySetIdTwice() {
        guitar.setId(10);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void shouldThrowUnsupportedOperationExceptionOnAttemptToCallSetNameTwice() {
        guitar.setName("Fender");
    }

    @Test(expected = UnsupportedOperationException.class)
    public void shouldThrowUnsupportedOperationExceptionOnAttemptToCallSetPriceTwice() {
        guitar.setPrice(new BigDecimal(2000));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void shouldThrowUnsupportedOperationExceptionOnAttemptToCallAnySetGuitarTypeTwice() {
        guitar.setGuitarType(GuitarType.ACOUSTIC);
    }
}
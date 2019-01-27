package com.epam.preprod.karavayev.shop.serializablelist;

import com.epam.preprod.karavayev.model.instrument.Guitar;
import com.epam.preprod.karavayev.model.instrument.StringInstrument;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;

public class ListSaverReaderTest {

    private File file = new File("test.ser");
    private ListSaverReader saverReader;
    private List<StringInstrument> list;


    @Before
    public void setUp() {
        saverReader = new ListSaverReader();
        list = fillOutContainer(10);
    }

    private List<StringInstrument> fillOutContainer(int amount) {
        List<StringInstrument> list = new ArrayList<>();
        IntStream.range(0, amount).forEach(i-> {
            list.add(new Guitar(i, "name" + i, new BigDecimal(100 + i)));
        });
        return list;
    }

    @Test
    public void shouldSaveContainerToFile() {
        saverReader.save(file, list, false);
        assertTrue(file.length() > 0);
    }

    @Test
    public void shouldReadContainerFromFile() {
        List<StringInstrument> list2 = saverReader.read(file);
        assertArrayEquals(list.toArray(), list2.toArray());
    }

    @Test
    public void shouldCheckSizesOfCommonFileAndCompressedFile() {
        File file1 = new File("file.ser");
        File file2 = new File("file.ser.gz");

        saverReader.writeObjectNTimesToOutputStream(file1, list, 10000, true);
        saverReader.writeToGZipFormat(file1, file2);
        long size1 = file1.length();
        long size2 = file2.length();
        System.out.println("Common file1 = " + size1 + " -> Gzipped file2 = " + size2 + ", compressed " + (size1 / size2) + " times");
    }
}
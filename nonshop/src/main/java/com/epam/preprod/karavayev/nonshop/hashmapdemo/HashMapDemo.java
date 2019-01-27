package com.epam.preprod.karavayev.nonshop.hashmapdemo;

import com.epam.preprod.karavayev.nonshop.stockkeepingunit.SKUnumber;
import com.epam.preprod.karavayev.nonshop.stockkeepingunit.SKUnumberOne;
import com.epam.preprod.karavayev.nonshop.stockkeepingunit.SKUnumberTwo;
import com.epam.preprod.karavayev.model.instrument.Guitar;
import com.epam.preprod.karavayev.model.instrument.StringInstrument;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class HashMapDemo {
    public static void main(String[] args) {
        Map<SKUnumber, StringInstrument> map1 = new HashMap<>();
        map1.put(new SKUnumberOne("Yamaha"), new Guitar(1, "Yamaha", new BigDecimal(500)));
        map1.put(new SKUnumberOne("Gibson"), new Guitar(2, "Gibson", new BigDecimal(500)));
        map1.put(new SKUnumberOne("Gibson"), new Guitar(2, "Gibson", new BigDecimal(500)));
        map1.put(new SKUnumberOne(null), new Guitar(2, "Gibson", new BigDecimal(500)));
        map1.put(new SKUnumberOne("abc"), new Guitar(2, "Gibson", new BigDecimal(500)));
        map1.put(new SKUnumberOne("ab"), new Guitar(2, "Gibson", new BigDecimal(500)));

        map1.forEach((k, v)-> System.out.println(k + " " + v));
        System.out.println();

        Map<SKUnumber, StringInstrument> map2 = new HashMap<>();
        map2.put(new SKUnumberTwo("Yamaha"), new Guitar(1, "Yamaha", new BigDecimal(500)));
        map2.put(new SKUnumberTwo("Gibson"), new Guitar(2, "Gibson", new BigDecimal(500)));
        map2.put(new SKUnumberTwo("Gibson"), new Guitar(2, "Gibson", new BigDecimal(500)));
        map2.put(new SKUnumberTwo(null), new Guitar(2, "Gibson", new BigDecimal(500)));
        map2.put(new SKUnumberTwo("abc"), new Guitar(2, "Gibson", new BigDecimal(500)));
        map2.put(new SKUnumberTwo("ab"), new Guitar(2, "Gibson", new BigDecimal(500)));

        map2.forEach((k, v)-> System.out.println(k + " " + v));
        System.out.println();

        Map<SKUnumber, StringInstrument> map3 = new LinkedHashMap<>();
        map3.put(new SKUnumberOne("Yamaha"), new Guitar(1, "Yamaha", new BigDecimal(500)));
        map3.put(new SKUnumberOne("Gibson"), new Guitar(2, "Gibson", new BigDecimal(500)));
        map3.put(new SKUnumberOne("Gibson"), new Guitar(2, "Gibson", new BigDecimal(500)));
        map3.put(new SKUnumberOne(null), new Guitar(2, "Gibson", new BigDecimal(500)));
        map3.put(new SKUnumberOne("abc"), new Guitar(2, "Gibson", new BigDecimal(500)));
        map3.put(new SKUnumberOne("ab"), new Guitar(2, "Gibson", new BigDecimal(500)));

        map3.forEach((k, v)-> System.out.println(k + " " + v));
        System.out.println();

        Map<SKUnumber, StringInstrument> map4 = new LinkedHashMap<>();
        map4.put(new SKUnumberTwo("Yamaha"), new Guitar(1, "Yamaha", new BigDecimal(500)));
        map4.put(new SKUnumberTwo("Gibson"), new Guitar(2, "Gibson", new BigDecimal(500)));
        map4.put(new SKUnumberTwo("Gibson"), new Guitar(2, "Gibson", new BigDecimal(500)));
        map4.put(new SKUnumberTwo(null), new Guitar(2, "Gibson", new BigDecimal(500)));
        map4.put(new SKUnumberTwo("abc"), new Guitar(2, "Gibson", new BigDecimal(500)));
        map4.put(new SKUnumberTwo("ab"), new Guitar(2, "Gibson", new BigDecimal(500)));

        map4.forEach((k, v)-> System.out.println(k + " " + v));
        System.out.println();
    }
}

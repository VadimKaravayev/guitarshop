package com.epam.preprod.karavayev.nonshop.stockkeepingunit;

import java.util.stream.IntStream;

public class SKUnumberTwo extends SKUnumber{
    public SKUnumberTwo(String key) {
        super(key);
    }

    @Override
    public int hashCode() {
        if (getKey() == null) {
            return 0;
        }
        int count = 4;
        String key = getKey();
        if (key.length() < count) {
            count = key.length();
        }
        return IntStream.range(0, count)
                .map(i-> i = getKey().toCharArray()[i])
                .sum();
    }

    @Override
    public String toString() {
        return String.valueOf(hashCode());
    }
}

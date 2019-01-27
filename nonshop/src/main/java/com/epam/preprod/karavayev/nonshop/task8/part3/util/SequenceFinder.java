package com.epam.preprod.karavayev.nonshop.task8.part3.util;

import java.util.HashMap;
import java.util.Map;

public class SequenceFinder {

    public Map<String, Integer> findOccurrences(byte[] bytes, int length) {
        Map<String, Integer> map = new HashMap<>();
        String str = new String(bytes);
        if (length > 0) {
            int limit = str.length() - length + 1;
            for (int i = 0; i < limit; i++) {
                String sub = str.substring(i, i + length);
                Integer counter = map.get(sub);
                if (counter == null) {
                    counter = 0;
                }
                map.put(sub, ++counter);
            }
        }
        return map;
    }

    public int[] getIndexesOfOccurrences(byte[] big, byte[] small) {
        int[] results = new int[2];
        String bigString = new String(big);
        String smallString = new String(small);
        int firstIndex = bigString.indexOf(smallString);
        int secondIndex = bigString.indexOf(smallString, firstIndex + 1);
        results[0] = firstIndex;
        results[1] = secondIndex;
        return results;
    }
}

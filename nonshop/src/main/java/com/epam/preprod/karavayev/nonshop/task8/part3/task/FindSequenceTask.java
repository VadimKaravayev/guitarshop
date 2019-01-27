package com.epam.preprod.karavayev.nonshop.task8.part3.task;

import com.epam.preprod.karavayev.nonshop.task8.myexception.WrongFileException;
import com.epam.preprod.karavayev.nonshop.task8.part3.app.SequenceAppClient;
import com.epam.preprod.karavayev.nonshop.task8.part3.util.SequenceFinder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class FindSequenceTask implements Runnable {

    private SequenceAppClient client;
    private SequenceFinder finder;

    public FindSequenceTask(SequenceAppClient client) {
        this.client = client;
        finder = new SequenceFinder();
    }

    @Override
    public void run() {
        while (true) {
            if (client.isFlag()) {
                byte[] bigBytes = new byte[0];
                try {
                    bigBytes = getBytesFromFile(client.getFile());
                } catch (WrongFileException e) {
                    client.setFlag(false);
                    System.err.println(e);
                }
                int length = bigBytes.length / 2;
                client.setLength(length);
                int max = 0;
                Map<String, Integer> occurrences = null;
                while (max < 2 && max != -1) {
                    occurrences = finder.findOccurrences(bigBytes, length);
                    client.setLength(length);
                    max = getMaxFromOccurrence(occurrences);
                    length--;
                }
                byte[] byteResult = getMaxBytes(max, occurrences);
                int[] indexes = finder.getIndexesOfOccurrences(bigBytes, byteResult);
                client.setByteResult(byteResult);
                client.setIndexEntries(indexes);
                client.setFlag(false);
            }
        }
    }

    private byte[] getMaxBytes(int max, Map<String, Integer> occurrences) {
        byte[] byteResult = new byte[0];
        if (occurrences != null) {
            for (Map.Entry<String, Integer> entry : occurrences.entrySet()) {
                if (entry.getValue() == max) {
                    byteResult = entry.getKey().getBytes();
                }
            }
        }
        return byteResult;
    }

    private byte[] getBytesFromFile(String file) {
        byte[] bigBytes = new byte[0];
        if (file != null && !file.isEmpty()) {
            Path path = Paths.get(file);
            try {
                bigBytes = Files.readAllBytes(path);
            } catch (IOException e) {
                throw new WrongFileException("Wrong file");
            }
        }
        return bigBytes;
    }

    private int getMaxFromOccurrence(Map<String, Integer> occurrences) {
        return occurrences.values().stream().mapToInt(v-> v).max().orElse(-1);
    }
}

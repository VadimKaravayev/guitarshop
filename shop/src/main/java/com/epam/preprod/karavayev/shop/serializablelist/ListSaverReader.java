package com.epam.preprod.karavayev.shop.serializablelist;

import com.epam.preprod.karavayev.model.instrument.StringInstrument;

import java.io.*;
import java.util.ArrayList;

import java.util.List;
import java.util.zip.GZIPOutputStream;

public class ListSaverReader {

    public List<StringInstrument> read(File file) {
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.err.println("IO exception");
            }
        }
        List<StringInstrument> list = new ArrayList<>();
        if (file.length() > 0) {
            try (FileInputStream fileInputStream = new FileInputStream(file);
                 ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);)
            {
                list = (List<StringInstrument>) objectInputStream.readObject();

            } catch (EOFException e) {
                System.err.println("EOF exception.");
            } catch (IOException e) {
                e.printStackTrace();
                System.err.println("IO exception.");
            } catch (ClassNotFoundException e) {
                System.err.println("Class not found");

            }
        }
        return list;
    }

    public void save(File file, List<StringInstrument> instruments, boolean append) {
        try (FileOutputStream fileOutputStream = new FileOutputStream(file, append);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);)
        {
            objectOutputStream.writeObject(instruments);
        } catch (IOException e) {
            System.err.println("Saving failed");
        }
    }

    public void writeObjectNTimesToOutputStream(File file, List<StringInstrument> instruments, int amount, boolean append) {
        try (FileOutputStream fileOutputStream = new FileOutputStream(file, append);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {

            for (int i = 0; i < amount; i++) {
                objectOutputStream.writeObject(instruments);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeToGZipFormat(File source, File gZipFile) {
        try (FileInputStream fileInputStream = new FileInputStream(source);
             FileOutputStream fileOutputStream = new FileOutputStream(gZipFile);
             GZIPOutputStream gzipOutputStream = new GZIPOutputStream(fileOutputStream))
        {
            byte[] buffer = new byte[1024];
            int len;
            while ((len = fileInputStream.read(buffer)) != -1) {
                gzipOutputStream.write(buffer, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

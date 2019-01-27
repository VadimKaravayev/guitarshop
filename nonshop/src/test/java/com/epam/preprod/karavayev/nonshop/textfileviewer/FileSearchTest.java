package com.epam.preprod.karavayev.nonshop.textfileviewer;

import com.epam.preprod.karavayev.nonshop.apputils.AppUtils;
import com.epam.preprod.karavayev.nonshop.apputils.ConsoleManager;
import com.epam.preprod.karavayev.nonshop.filefilters.*;
import com.epam.preprod.karavayev.nonshop.filesearch.FileSearch;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class FileSearchTest {

    private File file_depot;
    private File subfolder1;
    private File folder1;
    private FileSearch fileSearch;
    private FileFilter fileFilterName;
    private FileFilter fileFilterExtension;
    private FileFilter fileFilterSize;
    private FileFilter fileFilterDate;
    private ConsoleManager consoleManager = new ConsoleManager();
    private AppUtils appUtils;

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Before
    public void setUp() throws IOException {
        fileSearch = new FileSearch();
        appUtils = new AppUtils();
        subfolder1 = folder.newFolder("file_depot", "folder1", "subfolder1");
        folder1 = subfolder1.getParentFile();
        file_depot = subfolder1.getParentFile().getParentFile();
        new File(file_depot.getAbsolutePath() + File.separator + "spring.css").createNewFile();
        new File(file_depot.getAbsolutePath() + File.separator + "spring.html").createNewFile();
        new File(file_depot.getAbsolutePath() + File.separator + "spring.jpg").createNewFile();
        new File(file_depot.getAbsolutePath() + File.separator + "spring.xml").createNewFile();
        new File(file_depot.getAbsolutePath() + File.separator + "spring.txt").createNewFile();
        new File(file_depot.getAbsolutePath() + File.separator + "summer.txt").createNewFile();
        new File(folder1.getAbsolutePath() + File.separator + "summer.txt").createNewFile();
        new File(folder1.getAbsolutePath() + File.separator + "summer.xml").createNewFile();
        new File(folder1.getAbsolutePath() + File.separator + "winter.txt").createNewFile();
        new File(subfolder1.getAbsolutePath() + File.separator + "autumn.jpg").createNewFile();
        File f1 = new File(subfolder1.getAbsolutePath() + File.separator + "autumn.txt");
        f1.createNewFile();
        f1.setLastModified(appUtils.convertStringDateToLong("01-02-2018 12:00"));
        File f = new File(subfolder1.getAbsolutePath() + File.separator + "winter.html");
        f.createNewFile();
        writeToFile(f, "Et si tu n'existais pas\n" +
                "Dis-moi pourquoi j'existerais\n" +
                "Pour traîner dans un monde sans toi\n" +
                "Sans espoir et sans regret");
        f.setLastModified(appUtils.convertStringDateToLong("01-01-2018 12:00"));
    }

    private void writeToFile(File file, String line) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, true))) {
            bufferedWriter.write(line);
            bufferedWriter.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void shouldSearchForFileInSpecifiedDirectoryAndStoreThemInList() {

        assertEquals(12, fileSearch.getFilesOfDirectory(file_depot).size());
    }

    @Test
    public void shouldSearchFilesByName() {

        List<String> expectedList = new ArrayList<>();
        expectedList.add("summer.txt");
        expectedList.add("summer.xml");
        expectedList.add("summer.txt");

        List<File> files = fileSearch.getFilesOfDirectory(file_depot);
        fileFilterName = new FileFilterName("summer", null);
        List<File> result = appUtils.collectResults(files, fileFilterName);
        List<String> collect = result.stream().map(File::getName).collect(Collectors.toList());

        assertThat(expectedList, is(collect));
    }

    @Test
    public void shouldSearchByFileExtension() {
        List<File> expectedList = new ArrayList<>();
        expectedList.add(new File(file_depot.getAbsolutePath() + File.separator + "spring.css"));
        List<File> files = fileSearch.getFilesOfDirectory(file_depot);
        fileFilterExtension = new FileFilterExtension(".css", null);
        List<File> result = appUtils.collectResults(files, fileFilterExtension);
        assertThat(expectedList, is(result));
    }

    @Test
    public void shouldSearchBySizeRange() {
        List<File> expectedList = new ArrayList<>();
        expectedList.add(new File(subfolder1.getAbsolutePath() + File.separator + "winter.html"));
        List<File> files = fileSearch.getFilesOfDirectory(file_depot);
        fileFilterSize = new FileFilterSizeRange(100, 200, null);
        List<File> result = appUtils.collectResults(files, fileFilterSize);
        assertThat(expectedList, is(result));
    }

    @Test
    public void shouldSearchByDateModifiedRange() {
        List<File> expectedList = new ArrayList<>();

        expectedList.add(new File(subfolder1.getAbsolutePath() + File.separator + "autumn.txt"));
        expectedList.add(new File(subfolder1.getAbsolutePath() + File.separator + "winter.html"));

        long date1 = appUtils.convertStringDateToLong("01-01-2018 12:00");
        long date2 = appUtils.convertStringDateToLong("01-02-2018 12:00");
        List<File> files = fileSearch.getFilesOfDirectory(file_depot);
        fileFilterDate = new FileFilterDateModified(date1, date2, null);
        List<File> result = appUtils.collectResults(files, fileFilterDate);
        assertThat(expectedList, is(result));
    }

    @Test
    public void shouldSearchByFilterChain() {
        List<File> expectedList = new ArrayList<>();
        expectedList.add(new File(subfolder1.getAbsolutePath() + File.separator + "winter.html"));
        List<File> files = fileSearch.getFilesOfDirectory(file_depot);
        long date1 = appUtils.convertStringDateToLong("01-01-2018 12:00");
        long date2 = appUtils.convertStringDateToLong("01-02-2018 12:00");
        fileFilterDate = new FileFilterDateModified(date1, date2, null);
        fileFilterSize = new FileFilterSizeRange(10, 200, fileFilterDate);
        fileFilterExtension = new FileFilterExtension(".html", fileFilterSize);
        fileFilterName = new FileFilterName("winter", fileFilterExtension);
        List<File> result = appUtils.collectResults(files, fileFilterName);
        assertThat(expectedList, is(result));
    }
}

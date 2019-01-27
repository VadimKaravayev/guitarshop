package com.epam.preprod.karavayev.nonshop.application;

import com.epam.preprod.karavayev.nonshop.apputils.AppUtils;
import com.epam.preprod.karavayev.nonshop.apputils.ConsoleManager;
import com.epam.preprod.karavayev.nonshop.filefilters.*;
import com.epam.preprod.karavayev.nonshop.filesearch.FileSearch;

import java.io.File;
import java.util.List;

public class App {

    private ConsoleManager consoleManager;
    private FileSearch fileSearch;
    private AppUtils appUtils;

    public static void main(String[] args) {
        new App().run();
    }

    private App() {
        consoleManager = new ConsoleManager();
        fileSearch = new FileSearch();
        appUtils = new AppUtils();
    }

    private void run() {
        consoleManager.promptMessage("This is file searcher app based on chain responsibility pattern\n");
        String input = "";

        while (!input.equalsIgnoreCase(AppUtils.EXIT)) {
            FileFilter next = null;

            consoleManager.promptMessage("Please, tasktype in path of directory to search for files");
            consoleManager.promptMessage("Exit app(X)");

            String directory = appUtils.checkDirectoryInput(consoleManager.takeInput());

            if (appUtils.isExitInput(directory)) {
                return;
            }

            consoleManager.promptMessage("Search files by name? (0\\1)");
            input = appUtils.checkInput("0", "1", consoleManager.takeInput());

            if (input.equalsIgnoreCase("1")) {
                consoleManager.promptMessage("Type in a file name");
                String fileName = consoleManager.takeInput();
                next = new FileFilterName(fileName, next);
            }

            consoleManager.promptMessage("Search files by extension? (0\\1)");
            input = appUtils.checkInput("0", "1", consoleManager.takeInput());

            if (input.equalsIgnoreCase("1")) {
                consoleManager.promptMessage("Type in a file extension");
                String fileExtension = consoleManager.takeInput();
                next = new FileFilterExtension(fileExtension, next);
            }

            consoleManager.promptMessage("Search files by size range? (0\\1)");
            input = appUtils.checkInput("0", "1", consoleManager.takeInput());

            if (input.equalsIgnoreCase("1")) {
                consoleManager.promptMessage("Type in size 1");
                input = appUtils.checkSizeInput(consoleManager.takeInput());
                long size1 = appUtils.convertToLong(input);
                consoleManager.promptMessage("Type in size 2");
                input = appUtils.checkSizeInput(consoleManager.takeInput());
                long size2 = appUtils.convertToLong(input);
                next = new FileFilterSizeRange(size1, size2, next);
            }

            consoleManager.promptMessage("Search files by date modified range? (0\\1)");
            input = appUtils.checkInput("0", "1", consoleManager.takeInput());

            if (input.equalsIgnoreCase("1")) {
                consoleManager.promptMessage("Date must be in dd-MM-yyyy HH:mm format");
                consoleManager.promptMessage("Type in date 1");
                input = appUtils.checkInputDate(consoleManager.takeInput());
                long modifiedDate1 = appUtils.convertStringDateToLong(input);
                consoleManager.promptMessage("Type in date 2");
                input = appUtils.checkInputDate(consoleManager.takeInput());
                long modifiedDate2 = appUtils.convertStringDateToLong(input);
                next = new FileFilterDateModified(modifiedDate1, modifiedDate2, next);
            }

            List<File> filesOfDirectory = fileSearch.getFilesOfDirectory(new File(directory));
            List<File> results = appUtils.collectResults(filesOfDirectory, next);

            consoleManager.printList(results);

            consoleManager.promptMessage("Would you like to search again? (0\\1)");
            input = appUtils.checkInput("0", "1", consoleManager.takeInput());
            if (input.equalsIgnoreCase("0")) {
                consoleManager.promptMessage("See you next time");
                return;
            }
        }
    }
}

package helpers;

import utils.TestException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TextFileHelper {
    public static void saveToTxtFile(String fileName, String content) {
        String FILE_PATH = System.getProperty("user.dir") + File.separator + "target" + File.separator + fileName + ".txt";
        File file = new File(FILE_PATH);
        FileWriter writer;
        try {
            writer = new FileWriter(file);
        } catch (IOException e) {
            throw new TestException("Can't open file write", e);
        }
        try {
            writer.write(content);
        } catch (IOException e) {
            throw new TestException("Can't write string to file", e);
        }
        try {
            writer.close();
        } catch (IOException e) {
            throw new TestException("Can't close file writer", e);
        }
    }

    public static List<String> readFile(String fileName) {
        String FILE_PATH = System.getProperty("user.dir") + File.separator + "target" + File.separator + fileName + ".txt";
        File file = new File(FILE_PATH);
        return readFile(file);
    }

    public static List<String> readFile(File file) {
        List<String> content = new ArrayList<>();
        try {
            BufferedReader b = new BufferedReader(new FileReader(file));
            String readLine;
            while ((readLine = b.readLine()) != null) {
                content.add(readLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return content;
    }
}
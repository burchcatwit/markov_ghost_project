package myCode;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class FileManager extends professorCode.AbstractFileMonitor implements professorCode.FileTextReader, professorCode.FileTextWriter {
    String filePath;

    public FileManager(String filePath) {
        super(filePath);
        this.filePath = filePath;
    }

    @Override
    public void setFilePath(String path) throws IllegalArgumentException {
        if (path == null || path.trim().length() == 0) {
            throw new IllegalArgumentException("Error");
        }
        filePath = path;
    }

    @Override
    public String getFilePath() throws IllegalStateException {
        return filePath;
    }

    @Override
    public String readText(String path) throws IOException {
        String ot = "";
        File file = new File(path);
        BufferedReader br = new BufferedReader(new FileReader(file));
        String st;
        while ((st = br.readLine()) != null) {
            ot += st + "\n";
        }
        return ot;
    }

    @Override
    public Set<String> getAllLines(String path) throws IOException {
        Set<String> mySet = new HashSet<String>();
        File file = new File(path);
        BufferedReader br = new BufferedReader(new FileReader(file));
        String add;
        while ((add = br.readLine()) != null) {
            mySet.add(add);
        }
        return mySet;
    }

    @Override
    public String getLastLine(String path) throws IOException {
        File myFile = new File(path);
        try (Scanner reader = new Scanner(myFile)) {
            if (!myFile.exists() || !myFile.canRead()) throw new IOException();
            ArrayList<String> lines = new ArrayList<>();
            while (reader.hasNextLine()) {
                lines.add(reader.nextLine());
            }
            for (int i = lines.size() - 1; i >= 0; i--) {
                if (!(lines.get(i).trim().length() == 0)) {
                    return lines.get(i);
                }
            }
            return null;
        }
    }

    @Override
    public void writeToFile(String string, String path) throws IOException, IllegalArgumentException {
        File myFile = new File(path);
        try (PrintWriter writer = new PrintWriter(new FileWriter(myFile.getPath(), true))) {
            if (!myFile.exists() || !myFile.canWrite()) {
                throw new IOException();
            }
            if (string == null || string.trim().length() == 0) {
                throw new IllegalArgumentException();
            }
            writer.println(string);
        }
    }
}
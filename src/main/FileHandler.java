package main;

import java.io.*;
import java.util.ArrayList;

// singleton that provides basic read and write methods.
public class FileHandler {

    private static FileHandler instance;
    private Table table;
    private String PATH;

    private FileHandler() {
        ;
    }

    public static FileHandler getInstance() {
        if(instance == null)
            instance = new FileHandler();
        return instance;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    public Table getTable() {
        return table;
    }

    public String getPATH() {
        return this.PATH;
    }

    // reads a file by the given path; removes all whitespaces in cells, so it can be parsed into a table object
    public ArrayList<String[]> readFile(String PATH) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(PATH));
        ArrayList<String[]> list = new ArrayList<>();
        this.PATH = PATH;

        try(reader) {
            while (reader.ready()) {
                list.add(reader.readLine().split(","));
            }
        }

        return list;
    }

    // writes a file with the given table
    public void writeFile(Table table, String PATH) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(PATH));
        ArrayList<String[]> tableAsList = table.getTable();

        try(writer) {
            for (int i = 0; i < tableAsList.size(); i++) {
                for (int j = 0; j < tableAsList.get(i).length; j++) {
                    if (j != tableAsList.get(i).length - 1)
                        writer.write(tableAsList.get(i)[j] + ",");
                    else
                        writer.write(tableAsList.get(i)[j] + "\n");
                }
            }
        }
    }


}

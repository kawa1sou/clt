package main;

import java.io.*;
import java.util.ArrayList;

// FileHandler class is a singleton that provides basic read and write methods.
public class FileHandler {

    private static FileHandler instance;
    private BufferedReader reader;
    private BufferedWriter writer;
    private String PATH;
    private Table table;

    private FileHandler() {
        ;
    }

    public static FileHandler getInstance() {
        if(instance == null)
            instance = new FileHandler();
        return instance;
    }

    public void setPATH (String PATH) throws FileNotFoundException {
        if(PATH != null)
            reader = new BufferedReader(new FileReader(PATH));
        this.PATH = PATH;
    }

    public String getPATH() {
        return this.PATH;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    public Table getTable() {
        return table;
    }


    //Reads a file by the given earlier PATH. Beware - it removes all the whitespaces in cells!
    public ArrayList<String[]> readFile() throws IOException {
        ArrayList<String[]> list = new ArrayList<>();

            while (reader.ready()) {
                list.add(reader.readLine().split(","));
            }
        /*
        for(int i = 0; i < list.size(); i++) {
            String[] line = list.get(i);
            for(int j = 0; j < line.length; j++) {
                line[j] = line[j].replaceAll(" ", "");
            }

            list.set(i, line);
        }
         */

        return list;
    }

    //Writes to a file with a given list
    public void writeFile(ArrayList<String[]> list, String PATH) throws IOException {
        String prompt;
        writer = new BufferedWriter(new FileWriter(PATH));

        for(int i = 0; i < list.size(); i++) {
            for(int j = 0; j < list.get(i).length; j++) {
                if(j != list.get(i).length - 1)
                    writer.write(list.get(i)[j] + ",");
                else
                    writer.write(list.get(i)[j]);
            }
        }
    }


}
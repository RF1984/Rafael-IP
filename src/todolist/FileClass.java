package todolist;

import java.io.*;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.PrimitiveIterator;

public class FileClass {
    private ArrayList <> uploadList;

    public void saveFile(ArrayList<Tasks> taskList)
    {

        // If the file doesn't exists, create and write to it
        // If the file exists, truncate (remove all content) and write to it
        try (FileWriter writer = new FileWriter("tasklist.csv");
             BufferedWriter bw = new BufferedWriter(writer)) {

            bw.write(taskList.toString());

        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }
    }
    public void fileLoad (String filePath)
    {
        List taskListFromFile = new ArrayList();
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line = null;
        int count = 0;
        while ((line = reader.readLine())!=null);
        {
            if (count == 0) {
                count++;
                continue;
            }
            String[] lineContents = line.split("|");
            ArrayList<> uploadList = new ArrayList<>;




        }



    }
}

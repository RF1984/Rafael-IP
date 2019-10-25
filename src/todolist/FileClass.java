package todolist;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * this class deals with the .csv file. It allows to load the file when we start the program and read from it,
 * it also allows us to save our list into a file
 */

public class FileClass {
    private static final int numberOfFields = 4;    
    private static final int TASK = 0,
            PROJECT = 1,
            DATE = 2,
            STATUS = 3;

    /**
     * saves the tasklist in a .csv file.
     * if the file doesn't exist, it will create it
     *
     * @param taskList
     */
    public void saveFile(ArrayList<Task> taskList) {

        try (FileWriter writer = new FileWriter("tasklist.csv");
             BufferedWriter bw = new BufferedWriter(writer)) {
            taskList.stream()
                    .map(task -> task.toString() + "\n")
                    .forEach(string ->
                            {
                                try {
                                    bw.write(string);
                                } catch (IOException e) {
                                }
                            }
                    );


        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }
    }

    /**
     * checks if a file with tasks already exists and will read it.
     * @return tasks
     */
    public ArrayList<Task> upLoadFile() {
        Function<String, Task> creatTasks =
                record -> {
                    String[] parts = record.split("\\|");
                    if (parts.length == numberOfFields) {
                        try {
                            String taskTitle = parts[TASK].trim();
                            String projectName = parts[PROJECT].trim();
                            LocalDate dueDate = LocalDate.parse(parts[DATE].trim());
                            Boolean taskStatus = Boolean.parseBoolean(parts[STATUS].trim());
                            return new Task(taskTitle, projectName, dueDate, taskStatus);
                        } catch (NumberFormatException Exception) {
                            System.out.println("file not read" + record);
                            return null;
                        }
                    } else {
                        System.out.println("Wrong number of fields" + record);
                        return null;
                    }
                };
        ArrayList<Task> tasks;
        try {
            tasks = Files.lines(Paths.get("tasklist.csv"))
                    .map(creatTasks)
                    .filter(task1 -> task1 != null)
                    .collect(Collectors.toCollection(ArrayList::new));

        } catch (IOException e) {
            System.out.println("Unable to open " + "tasklist.csv");
            tasks = new ArrayList<>();
        }
        return tasks;
    }
}


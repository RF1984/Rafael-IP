package todolist;

import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;


/**
 * in this class we initialize our ArrayList of Tasks and write the methods to interact with that list
 */


public class Todolist {
    private ArrayList<Task> taskList;
    private FileClass fileClass;
    private Scanner userInput;

    /**
     * Constructor for todoList: Initializes the ArrayList, and the FileClass (used to write/save files)
     */
    public Todolist(InputStream source) {
        taskList = new ArrayList<>();
        fileClass = new FileClass();
        userInput = new Scanner(source);
    }

    /**
     * Scanner method will take input from the user in form of String
     *
     * @return a String typed by the user
     */
    public String scanner() {
        return userInput.nextLine();
    }

    /**
     * converts a to an Integer.
     * Will then check if this Integer contains a number that can be converted to Integer, and if it is a valid task number.
     *
     * @param indexString parameter is the string given by the user
     * @return -1 if the index is not valid, and the index if it succeeds.
     */
    public int convertStringToIndex(String indexString) {
        int index = -1;
        try {
            index = Integer.parseInt(indexString);
            if (index < 0 || index > taskList.size() - 1) {
                return -1;
            }
        } catch (NumberFormatException Exception) {
        }
        return index;
    }

    /**
     * Asks for the user to type a task number, and will convert it to sting calling the convertStringToIndex method.
     *
     * @return the index if it's valid, or will call the invalidTaskNumber method if it's not.
     */
    public int validateTaskNumber() {
        System.out.println("type task number:");
        int index = convertStringToIndex(scanner());
        if (index != -1) {
            return index;
        } else
            invalidTaskNumber();
        return validateTaskNumber();
    }

    /**
     * This is the first method called in the main class.
     * It will call a method to upload a file if one exists.
     * Will print a welcome message that will list the number of tasks on the users tasklist,
     * separating them by their status (done/undone)
     * will then automatically call the printOptions method
     */
    public void printWelcome() {
        taskList.addAll(fileClass.upLoadFile());
        System.out.println("=======================================");
        System.out.println("Welcome to Rafael Fernandes To Do List®");
        System.out.println("You already completed " + getCount(true) + " tasks,");
        System.out.println("But you still have " + getCount(false) + " unfinished tasks.");
        System.out.println("=======================================");
        printOptions();
    }

    /**
     * Print the first menu that the user can interact with. Will give some options and requires the
     * user to chose one.
     */
    public void printOptions() {
        System.out.println("==========================");
        System.out.println("Type your option number");
        System.out.println("1. See my tasks");
        System.out.println("2. Add a task to my list");
        System.out.println("3. Edit task");
        System.out.println("4. To save and exit");
        System.out.println("==========================");
        mainOptions();
    }

    /**
     * this method is called always after the Options menu is shown to the user.
     * it expects input from the user and links it to the related method.
     */
    public void mainOptions() {
        switch (scanner()) {
            case "1":
                printSortOptions();
                break;
            case "2":
                scannerAdd();
                break;
            case "3":
                printByDueDate();
                printEdit();
                break;
            case "4":
                printQuit();
                break;
            default:
                invalidOption();
                mainOptions();
        }
    }

    /**
     * Prints Quit message and save list into file
     */
    public void printQuit() {
        System.out.println("================================================");
        System.out.println("Thank you for using Rafael Fernandes To Do List®");
        System.out.println("================================================");
        fileClass.saveFile(taskList);
    }

    /**
     * Print Edit menu where the user can select in which way he wants to edit his/her tasks.
     */
    public void printEdit() {
        if (taskList.isEmpty()) {
            listEmptyWarning();
            printOptions();
        } else {
            System.out.println("=========================================");
            System.out.println("Choose one of the following edit options:");
            System.out.println("1 Change task status");
            System.out.println("2 Change task title");
            System.out.println("3 Change task project");
            System.out.println("4 Change task due date");
            System.out.println("5 Delete task");
            System.out.println("6 Go back");
            System.out.println("=========================================");
            editOptions();
        }
    }

    /**
     * this method is called always after the Edit Options menu is shown to the user.
     * it expects input from the user and links it to the related method.
     */
    public void editOptions() {
        switch (scanner()) {
            case "1":
                editStatus();
                break;
            case "2":
                editTitle();
                break;
            case "3":
                editProject();
                break;
            case "4":
                editDueDate();
                break;
            case "5":
                deleteTask();
                break;
            case "6":
                printOptions();
                break;
            default:
                invalidOption();
                editOptions();
        }
    }

    /**
     * Edits the Title of a task.
     * User is required to type the index of the task he wants to edit
     */
    public void editTitle() {
        int index = validateTaskNumber();
        System.out.println("type new title:");
        String newTaskTitle = scanner();
        taskList.get(index).changeTitle(newTaskTitle);
        System.out.println("task title changed");
        printByProject();
        printOptions();
    }

    /**
     * Edit the Project to which the a task belongs to.
     * User is required to type the index of the task he wants to edit
     */
    public void editProject() {
        int index = validateTaskNumber();
        System.out.println("type new project name");
        String newProjectName = scanner();
        taskList.get(index).changeProject(newProjectName);
        System.out.println("project name changed!");
        printByProject();
        printOptions();
    }

    /**
     * Edit the due date of a task.
     * User is required to type the index of the task he wants to edit
     */
    public void editDueDate() {
        int index = validateTaskNumber();
        System.out.println("type new due date (dd-mm-yyyy:)");
        String newDueDate = scanner();
        if (stringToDate(newDueDate) != null) {
            taskList.get(index).changeDueDate(stringToDate(newDueDate));
            printByProject();
            printOptions();
        } else {
            printOptions();
        }
    }

    /**
     * Edit the status of a task.
     * User is required to type the index of the task he wants to edit
     */
    public void editStatus() {
        int index = validateTaskNumber();
        taskList.get(index).done();
        System.out.println("Status changed!");
        printByProject();
        printOptions();
    }

    /**
     * add tasks to the task list
     * requires task name, project name and date.
     * status will be false (undone) by default
     */
    public void scannerAdd() {

        System.out.println("===============");
        System.out.println("type task name");
        String taskName = scanner();
        System.out.println("====================");
        System.out.println("type name of project");
        String taskProject = scanner();
        System.out.println("=================================================");
        System.out.println("type due date of task in this format: dd-MM-yyyy ");
        String dateString = scanner();
        LocalDate realDate = stringToDate(dateString);
        if (realDate != null) {
            Task task = new Task(taskName, taskProject, realDate);
            taskList.add(task);
            System.out.println("Good job! Task added!");
            printOptions();
        } else {
            scannerAdd();
        }
    }

    /**
     * this method takes the date typed by the user (String) to a LocalDate type.
     * it will throw an exception of the date is the wrong format.
     * it will also print an error message if the date typed by the user already passed.
     */
    public LocalDate stringToDate(String dateString) {
        // todo try and catch
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            LocalDate localDate = LocalDate.parse(dateString, formatter);
            if (localDate.compareTo(LocalDate.now()) < 0) {
                System.out.println("The date you typed belongs in the past, try again");
                return null;
            } else {
                return localDate;
            }
        } catch (DateTimeParseException exception) {
            System.out.println("wrong date format, try again");
            return null;
        }
    }

    /**
     * Deletes a task from the task list
     */
    public void deleteTask() {
        int taskNum = validateTaskNumber();
        taskList.remove(taskNum);
        System.out.println("Task number " + taskNum + " removed.");
        printOptions();
    }

    /**
     * prints an error message. This method is called when the user types an invalid input.
     */
    public void invalidOption() {
        System.out.println("Invalid Option. Try again");
    }

    /**
     * prints an error message.
     * This method is called when the user needs to type a task index, but types an inexistent one.
     */
    public void invalidTaskNumber() {
        System.out.println("That's not a valid task number");
    }

    public void listEmptyWarning() {
        System.out.println("Your list is still empty");
        System.out.println("Add some tasks first");
    }

    /**
     * print the possible options for print the task list
     * first checks if the list is empty, only if it's not, will then proceed.
     */
    public void printSortOptions() {
        if (taskList.isEmpty()) {
            listEmptyWarning();
            printOptions();
        } else {
            System.out.println("Choose one of the following options:");
            System.out.println("1. Sort list by project");
            System.out.println("2. Sort list by due date");
            sortOptions();
        }
    }

    /**
     * gives the actual sort options and asks for the user to choose one.
     */
    public void sortOptions() {
        switch (scanner()) {
            case "1":
                printByProject();
                printOptions();
                break;
            case "2":
                printByDueDate();
                printOptions();
                break;
            default:
                invalidOption();
                sortOptions();
        }
    }

    /**
     * print task list sorted by Project
     */
    public void printByProject() {
        taskList.stream().sorted(Comparator.comparing(Task::getProjectName))
                .forEach(p -> System.out.println(taskList.indexOf(p) + p.printTasks()));
    }

    /**
     * print task list sorted by due date
     */
    public void printByDueDate() {
        taskList.stream().sorted(Comparator.comparing(Task::getDueDate))
                .forEach(p -> System.out.println(taskList.indexOf(p) + p.printTasks()));
    }

    /**
     * gets the amount of tasks with true or false status. According to users input
     */
    public int getCount(Boolean taskStatus) {
        return (int) taskList.stream().filter(x -> x.getTaskStatus().equals(taskStatus)).count();
    }

    /**
     * this method returns a task from the our arraylist of tasks.
     * This method was created to assert the results of the testScannerAddTask() test.
     *
     * @param index
     * @return
     */
    public Task getListElement(int index) {
        return taskList.get(index);
    }
}
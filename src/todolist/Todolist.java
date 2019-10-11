package todolist;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;


/**
 * This class is the Main class of To do List application
 * This program should open a text based application where you can create a list of tasks to do. You can add tasks,
 * delete tasks, mark tasks as done.
 *  * @Author Rafael Fernandes
 */


public class Todolist
 {
        private Parser parser;
        private ArrayList<Tasks> taskList;


/**
* Constructor method. Creates an Arraylist of Tasks.
*/

    public Todolist()
        {
            taskList = new ArrayList<>();
        }


     /**
      * Print Welcome message and number of complete and uncomplete tasks.
      */
     public void printWelcome()
    {
        System.out.println("=======================================================================");
        System.out.println("Welcome to Rafael Fernandes To Do List®");
        System.out.println("You already completed " + getCount(true) + " tasks,");
        System.out.println("But you still have " + getCount(false) + " unfinished tasks.");
        System.out.println("=======================================================================");
        printOptions();
    }


     /**
      * Print Initial menu
      */
    public void printOptions()
    {
        System.out.println("=======================================================================");
        System.out.println("Type your option number");
        System.out.println("1. See my tasks");
        System.out.println("2. Add a task to my list");
        System.out.println("3. Remove a task from my list");
        System.out.println("4. Edit task");
        System.out.println("Type <quit> to save and exit");
        System.out.println("=======================================================================");
        Scanner keyboard = new Scanner(System.in);
        String printOption = keyboard.nextLine();

        switch (printOption) {
            case "1":
                printList();
                break;
            case "2":
                scannerAdd();
                break;
            case "3":
                deleteTask();
                break;
            case "4":
                printEdit();
                break;
            case "quit":
                printQuit();
                break;
        }
    }

     /**
      * Print Quit message
      */
    public void printQuit()
        {
            System.out.println("================================================");
            System.out.println("Thank you for using Rafael Fernandes To Do List®");
            System.out.println("================================================");
        }

     /**
      * Print Edit menu where the user can select in which way he wants to edit his/her tasks.
      */
    public void printEdit()
        {
            System.out.println("=========================================");
            System.out.println("Choose one of the following edit options:");
            System.out.println("1 Change task status");
            System.out.println("2 Change task title");
            System.out.println("3 Change task project");
            System.out.println("4 Change task due date");
            System.out.println("5 Go back");
            System.out.println("=========================================");
            Scanner keyboard = new Scanner(System.in);
            String editOption = keyboard.nextLine();

            switch (editOption) {
                case "1":
                    editStatus();
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
                    printOptions();
                    break;
            }
        }

     /**
      * Edit the Title of a task.
      * User is required to type the index of the task he wants to edit
      */
    public void editTitle()
        {
            System.out.println("type task index:");
            Scanner keyboard = new Scanner(System.in);
            int index = keyboard.nextInt();

            System.out.println("type new title:");
            Scanner keyboard2 = new Scanner(System.in);
            String newTaskTitle = keyboard2.nextLine();
            taskList.get(index).changeTitle(newTaskTitle);
            System.out.println("task title changed");

            printList();
        }

     /**
      * Edit the Project to which the a task belongs to.
      * User is required to type the index of the task he wants to edit
      */
    public void editProject()
        {
            System.out.println("type task index:");
            Scanner keyboard = new Scanner(System.in);
            int index = keyboard.nextInt();

            System.out.println("type new project name:");
            Scanner keyboard2 = new Scanner(System.in);
            String newProjectName = keyboard2.nextLine();
            taskList.get(index).changeProject(newProjectName);
            System.out.println("project name changed.");

            printList();
        }

     /**
      * Edit the due date of a task.
      * User is required to type the index of the task he wants to edit
      */
    public void editDueDate()
        {
            System.out.println("type task index:");
            Scanner keyboard = new Scanner(System.in);
            int index = keyboard.nextInt();

            System.out.println("type new due date (dd-mm-yyyy:)");
            Scanner keyboard2 = new Scanner(System.in);
            String newDueDate = keyboard2.nextLine();

            taskList.get(index).changeDueDate(stringToDate(newDueDate));
            System.out.println("project name changed.");

            printList();
        }


     /**
      * Edit the status of a task.
      * User is required to type the index of the task he wants to edit
      */
    public void editStatus()
        {
            System.out.println("insert task index");
            Scanner keyboard = new Scanner(System.in);
            int index = keyboard.nextInt();
            taskList.get(index).done(index);
            System.out.println("Status changed");
            printList();
        }

     /**
      * Print task list.
      */
    public void printList()
        {
            if (taskList.isEmpty())
            {
                System.out.println("Your list is still empty");
                System.out.println("Add some tasks first");
            }
            else
                {
                System.out.println("This is your task list:");
                for (int i = 0; i < taskList.size(); i++)
                    {
                    Tasks t = taskList.get(i);
                    System.out.print(i + " ");
                    t.printTasks();
                    //System.out.println("Task: " + t );
                    }
                }
                printOptions();
        }

     /**
      * add tasks to the task list
      * requires task name, project name and date.
      * status will be false (undone) when you enter a new task
      */
    public void scannerAdd()
    {
            Scanner keyboard = new Scanner(System.in);
            System.out.println("type task name");
            String taskName = keyboard.nextLine();
            System.out.println("type name of project");
            String taskProject = keyboard.nextLine();
            System.out.println("type due date of task in this format: dd-MM-yyyy ");
            String dateString = keyboard.nextLine();
            LocalDate realDate = stringToDate(dateString);

            if (realDate != null)
            {
                Tasks task = new Tasks(taskName, taskProject, realDate);
                taskList.add(task);
                printOptions();
            }
            else
                System.out.println("Error");
        }

    public LocalDate stringToDate(String dateString)
        {
            // todo try and catch
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                LocalDate localDate = LocalDate.parse(dateString, formatter);
                return localDate;
                }
            catch (DateTimeParseException exception)
                {
                System.out.println("wrong date format, try again");
                return null;
                }
        }

     /**
      *  Deletes a task from the task list
      */
     public void deleteTask()
        {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("type task number");
        int taskNum = keyboard.nextInt();
        taskList.remove(taskNum);
            System.out.println("Task number " + taskNum + " removed.");
            printOptions();
        }


    public void filter(String searchProject)
        {
            taskList.stream()
                    .filter(p -> searchProject.equals(p.getProjectName()));
        }

     /**
      * gets the amount of tasks with true or false status. According to users input
      */
     public int getCount (Boolean taskStatus)
        {
         return (int) taskList.stream().filter(x -> x.getTaskStatus().equals(taskStatus)).count();
        }

    public static void main(String[] args)
        {
            Todolist todolist = new Todolist();
            todolist.printWelcome();
        }
}
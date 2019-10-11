package todolist;

import java.time.LocalDate;

/** Task class will contain a HashMap where the Key is the task,
 * and the Value will be a Boolean where true means that task is DONE, and false
 * means the task is still UNDONE
 */

public class Tasks {
    private String taskTitle;
    private LocalDate dueDate;
    private Boolean taskStatus;
    private String projectName;
    private int count;


    //Contructor Create new task
    public Tasks (String taskTitle, String projectName, LocalDate dueDate)
        {
            this.taskTitle = taskTitle;
            this.projectName = projectName;
            this.dueDate = dueDate;
            taskStatus = false;

        }


    //Set task value (Status) True or False (Done or undone)
    public void done(int taskIndex)
        {
            if (taskStatus == true) {
                taskStatus = false;
            }
            else
            {
                taskStatus = true;
            }
        }

    //print task list
    public void printTasks()
        {
        System.out.println(" Task: " + taskTitle + " | Project: " + projectName + " | Due Date: " + dueDate +
                " | Status: " + ((taskStatus) ? "DONE" : "UNDONE"));
        }

    //enhancement. delete it if it make you confused


    @Override
    public String toString()
        {
        return taskTitle + "    " + projectName;
        }


    public String getProjectName()
        {
        return projectName;
        }



    public String getTaskTitle()
        {
        return taskTitle;
        }


    public LocalDate getDueDate()
        {
        return dueDate;
        }

    public Boolean getTaskStatus()
    {
        return taskStatus;
    }

    //change task title
    public void changeTitle(String newTaskTitle)
        {
        this.taskTitle = newTaskTitle;
        }

    public int getCount()
        {
            return count;
        }

    //change task project
    public void changeProject (String newProjectName)
        {
            this.projectName = newProjectName;
        }


        //change task due date
    public void changeDueDate (LocalDate newDueDate)
        {
        this.dueDate = newDueDate;
        }

}
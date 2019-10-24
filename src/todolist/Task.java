package todolist;

import java.time.LocalDate;

/**
 * This class creates the objects of Task.
 * a Task object represents a task that we have to do.
 * it will have a title, a project, a due date, and also a status (done/undone)
 */

public class Task {

    private String taskTitle;
    private LocalDate dueDate;
    private Boolean taskStatus;
    private String projectName;


    /**
     * Constructor method for Task. Takes 3 parameters, and assumes Status as false (task in undone)
     *
     * @param taskTitle
     * @param projectName
     * @param dueDate
     */
    public Task(String taskTitle, String projectName, LocalDate dueDate) {
        this.taskTitle = taskTitle;
        this.projectName = projectName;
        this.dueDate = dueDate;
        taskStatus = false;
    }

    /**
     * Second constructor for Tasl. It takes 4 parameters.
     * It's used when reading a task from a file.
     *
     * @param taskTitle
     * @param projectName
     * @param dueDate
     * @param taskStatus
     */
    public Task(String taskTitle, String projectName, LocalDate dueDate, Boolean taskStatus) {
        this.taskTitle = taskTitle;
        this.projectName = projectName;
        this.dueDate = dueDate;
        this.taskStatus = taskStatus;
    }

    /**
     * Alternates the Status of a task from true (done) to false (undone) or vice versa.
     */
    public void done() {
        if (taskStatus == true) {
            taskStatus = false;
        } else {
            taskStatus = true;
        }
    }

    /**
     * print task list
     */
    public String printTasks() {
        return " Task: " + taskTitle + " | Project: " + projectName + " | Due Date: " + dueDate +
                " | Status: " + ((taskStatus) ? "DONE" : "UNDONE");
    }

    @Override
    public String toString() {
        return taskTitle + " | " + projectName + " | " + dueDate +
                " | " + taskStatus;
    }

    /**
     * get method
     *
     * @return Project name
     */
    public String getProjectName() {
        return projectName;
    }

    /**
     * get method
     *
     * @return Task title
     */
    public String getTaskTitle() {
        return taskTitle;
    }

    /**
     * get method
     *
     * @return
     */
    public LocalDate getDueDate() {
        return dueDate;
    }

    /**
     * get method
     *
     * @return Task status
     */
    public Boolean getTaskStatus() {
        return taskStatus;
    }

    /**
     * set method. sets new task title
     *
     * @param newTaskTitle
     */
    public void changeTitle(String newTaskTitle) {
        this.taskTitle = newTaskTitle;
    }

    /**
     * set method. sets new project name
     *
     * @param newProjectName
     */
    public void changeProject(String newProjectName) {
        this.projectName = newProjectName;
    }

    /**
     * set method. sets new task due date
     *
     * @param newDueDate
     */
    public void changeDueDate(LocalDate newDueDate) {
        this.dueDate = newDueDate;
    }

}